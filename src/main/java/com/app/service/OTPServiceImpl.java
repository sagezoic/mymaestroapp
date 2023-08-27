//package com.app.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.app.dao.OTPDao;
//import com.app.dto.VerifyOTPResponseDTO;
//import com.app.entities.OTPRecord;
//
//import java.time.LocalDateTime;
////import java.util.HashMap;
////																																																																																																																																																																																																																																																																																																																																import java.util.Map;
//import java.util.Optional;
//import java.util.Random;
//
//@Service
//@Transactional
//public class OTPServiceImpl implements OTPService {
//	
//    private static final String DIGITS = "0123456789";
//    private static final int OTP_LENGTH = 4;
//    
//																																																																																																																																																																																																																																																																																																																																																																																																																			    // In-memory storage				
//    //private final Map<String, String> otpStorage = new HashMap<>(); // In-memory storage
//    
//    @Autowired
//    private JavaMailSender javaMailSender;
//    
//    @Autowired
//    private final OTPDao otpDao;
//    
//    @Autowired
//    public OTPServiceImpl(JavaMailSender javaMailSender,OTPDao otpDao) {
//        this.javaMailSender = javaMailSender;
//        this.otpDao = otpDao;
//    }
//    
//    //======================= To check if entered email already exist in database ======================
//    
////  @Override
////  public boolean isEmailAlreadyRegistered(String email) {
////      return userRepository.findByEmail(email) != null;
////  }
// 
//   //========================= To Generate and Send OTP via Email ===================================== 
//    
//	@Override
//	public String generateOTP() {
//	   
//    	 Random random = new Random();// Implement OTP generation logic  using a random number generator.
//         StringBuilder otp = new StringBuilder(OTP_LENGTH);// Return the generated OTP
//         
//         for (int i = 0; i < OTP_LENGTH; i++) {
//             otp.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
//         }
//         System.out.println(otp.toString());
//         return otp.toString();
//	}
//    
//
//
//	@Override
//	public void sendOTPByEmail(String email, String otp) {
//		
//		SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Your OTP Code");
//        message.setText("Your OTP code is: " + otp);
//
//        javaMailSender.send(message);
//		
//	}
//	
//	
//    @Override
//    @Transactional
//    public void storeOTPInBackend(String email, String otp) {
//       // otpStorage.put(email, otp);
//    	 OTPRecord otpRecord = new OTPRecord();
//         otpRecord.setEmail(email);
//         otpRecord.setOtp(otp);
//         otpRecord.setTimestamp(LocalDateTime.now().plusMinutes(5)); // OTP validity period
//
//         otpDao.save(otpRecord);
//    }
//    
//    
//	 //========================= To Receive and Verify OTP Entered by user ===================================== ===
//    
////	  @Override
////	    public VerifyOTPResponseDTO verifyOTP(String email, String enteredOTP) {
////	        
////		  String storedOTP = otpStorage.get(email);
////
////	        VerifyOTPResponseDTO response = new VerifyOTPResponseDTO(email, "");
////
////	        if (storedOTP != null && storedOTP.equals(enteredOTP)) {
////	            response.setMessage("OTP verification succeeded!");
////	        } else {
////	            response.setMessage("OTP verification failed!");
////	        }
////
////	        return response;
////	    }
////
////	    @Override
////	    @Transactional(readOnly = true)
////	    public boolean verifyOTP(String email, String enteredOTP) {
////	        Optional<OTPRecord> otpRecordOptional = otpRecordRepository.findLatestValidOTPByEmail(email);
////
////	        if (otpRecordOptional.isPresent()) {
////	            OTPRecord otpRecord = otpRecordOptional.get();
////
////	            if (otpRecord.getOtp().equals(enteredOTP)) {
////	                // Validate timestamp to ensure OTP is still valid
////	                return otpRecord.getTimestamp().isAfter(LocalDateTime.now());
////	            }
////	        }
////      return false;
////	  }
//	
//	
//
//    @Override
//    @Transactional(readOnly = true)
//    public VerifyOTPResponseDTO verifyOTP(String email, String enteredOTP) {
//        Optional<OTPRecord> otpRecordOptional = otpDao.findLatestValidOTPByEmail(email);
//
//        VerifyOTPResponseDTO response = new VerifyOTPResponseDTO();
//        response.setEmail(email);
//
//      if (otpRecordOptional.isPresent()) {
//            OTPRecord otpRecord = otpRecordOptional.get();
//
//          if (otpRecord.getOtp().equals(enteredOTP)) {
//               if (otpRecord.getTimestamp().isAfter(LocalDateTime.now())) {
//                    response.setSuccess(true);
//                    response.setMessage("OTP verification succeeded!");
//                } 
//          
//               else {
//                    response.setSuccess(false);
//                    response.setMessage("OTP has expired.");	
//                }
//           } 
//         else {
//                response.setSuccess(false);
//                response.setMessage("Invalid OTP entered.");
//            }
//        } 
//      else {
//            response.setSuccess(false);
//            response.setMessage("No valid OTP found for this email.");
//        }
//
//        return response;
//    }
//
//}
