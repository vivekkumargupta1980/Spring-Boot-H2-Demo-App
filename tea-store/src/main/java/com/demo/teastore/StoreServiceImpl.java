package com.demo.teastore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.teastore.entity.StoreOrderEntity;
import com.demo.teastore.entity.StoreOrderItemEntity;
import com.demo.teastore.entity.TeaEntity;
import com.demo.teastore.entity.UserEntity;
import com.demo.teastore.persistence.StoreOrderEntityRepository;
import com.demo.teastore.persistence.TeaEntityRepository;
import com.demo.teastore.persistence.UserEntityRepository;
import com.demo.teastore.utility.DataWarehouseProcessor;

@Service
public class StoreServiceImpl implements StoreService {
	private UserEntityRepository userEntityRepository; 
	private TeaEntityRepository teaEntityRepository;
	private StoreOrderEntityRepository storeOrderEntityRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public StoreServiceImpl(UserEntityRepository userEntityRepository, TeaEntityRepository teaEntityRepository,
			StoreOrderEntityRepository storeOrderEntityRepository, ModelMapper modelMapper) {
		this.userEntityRepository = userEntityRepository;
		this.teaEntityRepository = teaEntityRepository;
		this.storeOrderEntityRepository = storeOrderEntityRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public List<StoreOrder> getAllOrders() {
		List<StoreOrderEntity> storeOrderEntities= storeOrderEntityRepository.findAll();
		List<StoreOrder> storeOrderEntityList = new ArrayList<>();
		storeOrderEntities.forEach(storeOrderEntity -> {
			StoreOrder storeOrder = convertToDTO(storeOrderEntity);
			storeOrderEntityList.add(storeOrder);
		});
		
		return storeOrderEntityList;
	}

	@Override
	public StoreOrderEntity createOrder(StoreOrder storeOrder) {
		StoreOrderEntity storeOrderEntity = convertToEntity(storeOrder);
		if (storeOrderEntity.getUser().getId() != null) {
			Optional<UserEntity> optionalUserEntity = userEntityRepository.findById(storeOrderEntity.getUser().getId());
			if (optionalUserEntity.isPresent()) {
				storeOrderEntity.setUser(optionalUserEntity.get());
			}
		}
		Set<StoreOrderItemEntity> storeOrderItemEntities = storeOrderEntity.getItems();
		storeOrderItemEntities.forEach(storeOrderItemEntity -> {
			if (storeOrderItemEntity.getTea().getId() != null) {
				Optional<TeaEntity> optionalTeaEntity = teaEntityRepository.findById(storeOrderItemEntity.getTea().getId());
				if (optionalTeaEntity.isPresent()) {
					storeOrderItemEntity.setTea(optionalTeaEntity.get());
				}
			}
			storeOrderItemEntity.setStoreOrder(storeOrderEntity);
		});
		DataWarehouseProcessor.processOrderUsingLongAlgorithm(storeOrderEntity.getId());;
		StoreOrderEntity sOrderEntity = storeOrderEntityRepository.saveAndFlush(storeOrderEntity);
		return sOrderEntity;
	}
	
	@Override
	public List<StoreOrder> getOrderByUserId(Long userId) {
		List<StoreOrderEntity> storeOrderEntities = storeOrderEntityRepository.findOrderByUserId(userId);
		List<StoreOrder> storeOrders = new ArrayList<>();
		storeOrderEntities.forEach(storeOrderEntity -> {
			StoreOrder storeOrder = convertToDTO(storeOrderEntity);
			storeOrders.add(storeOrder);
		});
		return storeOrders;
	}
	
	@Override
	public StoreOrder getOrder(Long orderId) {
		Optional<StoreOrderEntity> optionalStoreOrderEntity = storeOrderEntityRepository.findById(orderId);
		if (optionalStoreOrderEntity.isPresent()) {
			return convertToDTO(optionalStoreOrderEntity.get());
		} else {
			return null;
		}
	}
	
	@Override
	public StoreOrderEntity deleteOrder(Long orderId) {
		Optional<StoreOrderEntity> optionalStoreOrderEntity = storeOrderEntityRepository.findById(orderId);
		if (optionalStoreOrderEntity.isPresent()) {
			StoreOrderEntity storeOrderEntity = optionalStoreOrderEntity.get();
			storeOrderEntity.setUser(null);
			storeOrderEntity.getItems().forEach(item -> {
				item.setTea(null);
			});
			storeOrderEntityRepository.delete(storeOrderEntity);
			return storeOrderEntity;
		} else {
			return null;
		}
	}
	
	private StoreOrderEntity convertToEntity(StoreOrder storeOrder) {
        StoreOrderEntity storeOrderEntity = modelMapper.map(storeOrder, StoreOrderEntity.class);
        return storeOrderEntity;
    }
	
	private StoreOrder convertToDTO(StoreOrderEntity storeOrderEntity) {
		StoreOrder storeOrder = modelMapper.map(storeOrderEntity, StoreOrder.class);
	    return storeOrder;
	}
}
