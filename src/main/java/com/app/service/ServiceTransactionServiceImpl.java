package com.app.service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.app.dao.ServceDao;
import com.app.dao.ServiceRequestDao;
import com.app.dao.ServiceTransactionDao;
import com.app.dao.UserDao;
import com.app.dto.ServiceTransactionRequestDTO;
import com.app.entities.Servce;
import com.app.entities.ServiceRequest;
import com.app.entities.ServiceTransaction;
import com.app.entities.Users;

import custom_exception.ResourceNotFoundException;

@Service
public class ServiceTransactionServiceImpl implements ServiceTransactionService {

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Autowired
	private ServiceTransactionDao serviceTransactionDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ServceDao serviceDao;
	
	@Autowired
	private ServiceRequestDao serviceRequestDao;
	
	
	public void addTransaction(ServiceTransactionRequestDTO dto) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					Users explorer = userDao.findById(dto.getSenderUserId()).orElseThrow(()->new ResourceNotFoundException("User is invalid"));  
					if(explorer.getToken() >= dto.getAmount()) {
						Users maestro = userDao.findById(dto.getReciverUserId()).orElseThrow(()->new ResourceNotFoundException("User is invalid"));
						Servce service = serviceDao.findById(dto.getServiceId()).orElseThrow(()->new ResourceNotFoundException("service id is invalid"));
						ServiceRequest serviceRequest = serviceRequestDao.findByServiceId(service);
						ServiceTransactionRequestDTO serviceRequestDTO =
								new ServiceTransactionRequestDTO(dto.getAmount(),true,dto.getSenderUserId() ,dto.getReciverUserId(),dto.getServiceId() ,dto.getPaymentMethod());
						ServiceTransaction serviceTransaction = mapper.map(serviceRequestDTO, ServiceTransaction.class);
						serviceTransaction.setServiceRequestId(serviceRequest);
						ServiceTransaction PersitanceserviceTransaction=serviceTransactionDao.save(serviceTransaction);
						explorer.setToken(explorer.getToken() - dto.getAmount());	
						maestro.setToken(maestro.getToken() + dto.getAmount());
						userDao.save(explorer);
						userDao.save(maestro);
						explorer.addExplorerTransaction(PersitanceserviceTransaction);
						maestro.addMaestroTransaction(PersitanceserviceTransaction);
						service.addServiceTransaction(PersitanceserviceTransaction);
					}
					else 
					{
						throw new Exception();
					}	
					
				}catch(Exception e) {
					status.setRollbackOnly();
					throw new ResourceNotFoundException("Transaction Failed");
				}
				
			}
		});
		
	}
	
	
	

}