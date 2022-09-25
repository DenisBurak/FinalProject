package com.zemelya;

import com.zemelya.service.car.CarService;
import com.zemelya.service.user.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {

    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext("com.zemelya");

    UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

//    User user = new User();
//    user.setUserName("JDBC");
//    user.setSurname("Template");
//    user.setBirth(new Timestamp(new Date().getTime()));
//    user.setRoleId(2);
//    user.setCreationDate(new Timestamp(new Date().getTime()));
//    user.setModificationDate(new Timestamp(new Date().getTime()));
//    user.setIsDeleted(false);
//    user.setEmail("someEmail@gmail.com");
//    user.setLogin("JDBCLogin");
//    user.setPassword("JDBCPassword");
//
//    System.out.println(user);
//
//    User user1 = userService.create(user);
//    System.out.println(user1);
//
//    user1.setIsDeleted(true);
//    User user2 = userService.update(user1);
//    System.out.println(user2);

//    System.out.println(userService.delete(10));

//    DrivingLisenceService drivingLisenceService = annotationConfigApplicationContext.getBean(DrivingLisenceService.class);
//
//    DrivingLisence drivingLisence = new DrivingLisence();
//    drivingLisence.setSerialNumber("ASD1123");
//    drivingLisence.setCategory("B,C");
//    drivingLisence.setDateOfIssue(new Timestamp(new Date().getTime()));
//    drivingLisence.setExpirationDate(new Timestamp(new Date().getTime()));
//    drivingLisence.setUserId(11);
//    drivingLisence.setIsDeleted(false);
//    drivingLisence.setCreationDate(new Timestamp(new Date().getTime()));
//    drivingLisence.setModificationDate(new Timestamp(new Date().getTime()));
//
//    System.out.println(drivingLisence);
//
//    DrivingLisence drivingLisence1 = drivingLisenceService.create(drivingLisence);
//    System.out.println(drivingLisence1);
//
//    drivingLisence1.setIsDeleted(true);
//    DrivingLisence drivingLisence2 = drivingLisenceService.update(drivingLisence1);
//    System.out.println(drivingLisence2);

//    drivingLisenceService.delete(6l);

    CarService carService = annotationConfigApplicationContext.getBean(CarService.class);

//    Car car = new Car();
//    car.setModelId(10);
//    car.setVolume(2.7D);
//    car.setVinNumber("456456HHH456456DDDDD54");
//    car.setIsAvailable(true);
//    car.setCreationDate(new Timestamp(new Date().getTime()));
//    car.setModificationDate(new Timestamp(new Date().getTime()));
//    car.setPrice(660.50D);
//
//    System.out.println(car);
//
//    Car car1 = carService.create(car);
//    System.out.println(car1);

//    carService.delete(31l);

//    List<Map<String, Object>> stringObjectList = carService.getListOfCars();
//    stringObjectList.forEach(System.out::println);

//    List<Map<String, Object>> listOfAvailableCars = carService.getListOfAvailableCars(new Timestamp(new Date().getTime()));
//    listOfAvailableCars.forEach(System.out::println);

  }
}
