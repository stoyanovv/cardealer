package com.cardealer.cars.service.impl;

import com.cardealer.cars.common.OutputJson;
import com.cardealer.cars.model.binding.CarBindingModel;
import com.cardealer.cars.model.entity.Car;
import com.cardealer.cars.model.entity.Purchase;
import com.cardealer.cars.model.entity.User;
import com.cardealer.cars.model.view.CarInfoView;
import com.cardealer.cars.model.view.CarView;
import com.cardealer.cars.repository.CarRepository;
import com.cardealer.cars.service.CarService;
import com.cardealer.cars.service.PurchaseService;
import com.cardealer.cars.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final UserService userService;
    private final PurchaseService purchaseService;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, UserService userService, PurchaseService purchaseService, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.userService = userService;
        this.purchaseService = purchaseService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCars() {
        if (carRepository.count() == 0) {
            carRepository.save(new Car("Мерцедес", "S-класа", LocalDate.of(2016, 2, 3),
                    "Дизел", BigDecimal.valueOf(62000), 3000, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/2019_Mercedes-Benz_S350d_L_AMG_Line_Executive_3.0_Front.jpg/1200px-2019_Mercedes-Benz_S350d_L_AMG_Line_Executive_3.0_Front.jpg"));
            carRepository.save(new Car("Ауди", "А4", LocalDate.of(2018, 8, 24),
                    "Дизел", BigDecimal.valueOf(58500), 2000, "https://www.automotiveaddicts.com/wp-content/uploads/2018/07/2018-audi-a4-quattro.jpg"));
            carRepository.save(new Car("Порше", "Кайен", LocalDate.of(2015, 3, 3),
                    "Дизел", BigDecimal.valueOf(81900), 4000, "https://www.driver.bg/wp-content/uploads/2014/07/12.jpg"));
            carRepository.save(new Car("Тойота", "RAV4", LocalDate.of(2015, 9, 20),
                    "Дизел", BigDecimal.valueOf(54000), 2000, "https://www.carpixel.net/w/ea3c2304b56ee2612bf5b8ad8f58dd64/toyota-rav4-hybrid-car-wallpaper-33028.jpg"));
            carRepository.save(new Car("Волво", "XC60", LocalDate.of(2016, 7, 14),
                    "Дизел", BigDecimal.valueOf(57200), 3000, "https://www.newcartestdrive.com/wp-content/uploads/2015/08/2016-volvo-xc60.jpg"));
            carRepository.save(new Car("Мерцедес", "E-класа", LocalDate.of(2014, 5, 7),
                    "Дизел", BigDecimal.valueOf(45000), 2200, "https://s.car.info/image_files/360/mercedes-benz-e-class-coupe-front-side-2-353766.jpg"));
            carRepository.save(new Car("Фолксваген", "Голф", LocalDate.of(2012, 6, 14),
                    "Дизел", BigDecimal.valueOf(11400), 1900, "https://www.carpixel.net/w/7c4c2d43c4ee24f985abd798163a2439/volkswagen-golf-5-door-car-wallpaper-44195.jpg"));
            carRepository.save(new Car("Ферари", "458", LocalDate.of(2014, 11, 2),
                    "Бензин", BigDecimal.valueOf(186400), 4000, "https://hips.hearstapps.com/hmg-prod/amv-prod-cad-assets/images/13q4/543504/2014-ferrari-458-speciale-first-drive-review-car-and-driver-photo-551761-s-original.jpg?fill=2:1&resize=1200:*"));
            carRepository.save(new Car("БМВ", "7-ма серия", LocalDate.of(2019, 6, 3),
                    "Бензин", BigDecimal.valueOf(82000), 5000, "https://i.ytimg.com/vi/RqZTdqAGsjU/maxresdefault.jpg"));
            carRepository.save(new Car("Ауди", "А8", LocalDate.of(2020, 2, 23),
                    "Дизел", BigDecimal.valueOf(98000), 3000, "https://autobild.bg/wp-content/uploads/2020/06/Audi-A8-1200x800-cc0b7cc1e11ef67f-Copy-720x480.jpg"));
            carRepository.save(new Car("Бугати", "Chiron", LocalDate.of(2018, 12, 5),
                    "Бензин", BigDecimal.valueOf(264000), 5000, "https://cdn.motor1.com/images/mgl/kLnzB/s1/2017-bugatti-chiron-first-drive.jpg"));
            carRepository.save(new Car("Шкода", "Кодиак", LocalDate.of(2019, 11, 17),
                    "Дизел", BigDecimal.valueOf(74400), 3000, "https://i2.wp.com/moyoauto.ru/wp-content/uploads/2018/07/skoda-kodiaq-scout.jpg"));
            carRepository.save(new Car("Ауди", "А6", LocalDate.of(2017, 7, 3),
                    "Бензин", BigDecimal.valueOf(64000), 2000, "https://cdn.car-recalls.eu/wp-content/uploads/2020/05/Audi-A6-2020-recall-rsg-fire-768x432.jpg"));
            carRepository.save(new Car("Мазда", "CX-5", LocalDate.of(2010, 1, 29),
                    "Дизел", BigDecimal.valueOf(19800), 3000, "https://media.ed.edmunds-media.com/mazda/cx-5/2014/oem/2014_mazda_cx-5_4dr-suv_grand-touring_fq_oem_4_1600.jpg"));
            carRepository.save(new Car("Мерцедес", "C-класа", LocalDate.of(2015, 9, 3),
                    "Дизел", BigDecimal.valueOf(36000), 2000, "https://s1.cdn.autoevolution.com/images/news/2015-mercedes-c-class-sedan-us-pricing-announced-84593_1.jpg"));
            carRepository.save(new Car("БМВ", "5-та серия", LocalDate.of(2017, 1, 8),
                    "Дизел", BigDecimal.valueOf(56000), 3000, "https://www.driver.bg/wp-content/uploads/2017/11/bmw-5-series-2017.jpg"));
            carRepository.save(new Car("Волво", "XC90", LocalDate.of(2016, 2, 9),
                    "Дизел", BigDecimal.valueOf(83600), 3000, "https://i.pinimg.com/originals/5e/09/24/5e0924cd6d93d757b808882813667bbf.jpg"));
            carRepository.save(new Car("Мазда", "6", LocalDate.of(2009, 4, 19),
                    "Дизел", BigDecimal.valueOf(16700), 2000, "https://images.ams.bg/images/galleries/108623/mazda-6-1460787774_big.jpg"));
            carRepository.save(new Car("Мерцедес", "ML", LocalDate.of(2011, 5, 17),
                    "Дизел", BigDecimal.valueOf(23300), 3000, "http://img.autoabc.lv/Mercedes-ML/Mercedes-ML_2008_Apvidus_1641255720_12.jpg"));
            carRepository.save(new Car("Фолксваген", "Пасат", LocalDate.of(2013, 7, 23),
                    "Дизел", BigDecimal.valueOf(18800), 3000, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/VW_Passat_B8_Limousine_2.0_TDI_Highline.JPG/1200px-VW_Passat_B8_Limousine_2.0_TDI_Highline.JPG"));
            carRepository.save(new Car("Тойота", "Land Cruiser", LocalDate.of(2014, 8, 10),
                    "Дизел", BigDecimal.valueOf(75600), 4000, "https://1cars.org/uploads/posts/2015-08/1439393959_toyota-land-cruiser-200-3.jpg"));
        }
    }

    @Override
    public List<CarView> getAllCarsAvailable() {
        List<CarView> carViews = new ArrayList<>();
        for (Car car : carRepository.getAllByBought(false)) {
                carViews.add(modelMapper.map(car, CarView.class));
        }
        return carViews;
    }

    @Override
    public CarInfoView getInfoForCar(Long carId) {
        return modelMapper.map(carRepository.getById(carId), CarInfoView.class);
    }

    @Override
    public OutputJson getBoughtCars(Long id) {
        OutputJson outputJson = new OutputJson();
        List<CarView> carViews = new ArrayList<>();
        BigDecimal price = BigDecimal.valueOf(0);
        for (Car car : carRepository.getAllByUserId(id)) {
            carViews.add(modelMapper.map(car, CarView.class));
            price = price.add(car.getPrice());
        }
        outputJson.setCars(carViews);
        outputJson.setSuccess(true);
        outputJson.setPrice(price);
        return outputJson;
    }

    @Override
    public OutputJson buyCar(Long id, Long carId) {
        OutputJson outputJson = new OutputJson();
        Car car = carRepository.getById(carId);
        if (car == null) {
            outputJson.setSuccess(false);
            outputJson.setMessage("Няма такъв автомобил");
            return outputJson;
        }
        car.setBought(true);
        User user = userService.getById(id);
        if (user == null) {
            outputJson.setSuccess(false);
            outputJson.setMessage("Няма такъв потребител");
            return outputJson;
        }
        Purchase purchase = modelMapper.map(car, Purchase.class);
        purchase.setUser(user);
        purchase.setTimeOfPurchase(LocalDateTime.now());
        purchaseService.save(purchase);
        car.setUser(user);
        user.getCars().add(car);
        carRepository.save(car);
        userService.save(user);
        outputJson.setSuccess(true);
        outputJson.setMessage("Успешно добавихте автомобил " + car.getMake() + " за " + car.getPrice() + " към вашите покупки");
        return outputJson;
    }

    @Override
    public OutputJson deleteCar(Long id, Long carId) {
        OutputJson outputJson = new OutputJson();
        Car car = carRepository.getById(carId);
        if (car == null) {
            outputJson.setSuccess(false);
            outputJson.setMessage("Няма такъв автомобил");
            return outputJson;
        }
        car.setBought(false);
        car.setUser(null);
        carRepository.save(car);
        outputJson.setSuccess(true);
        outputJson.setMessage("Успешно премахнахте автомобил " + car.getMake() + " от вашите покупки");
        return outputJson;
    }

    @Override
    public List<CarView> searchForMake(String make) {
        List<CarView> carViews = new ArrayList<>();
        for (Car car : carRepository.getAllByMakeAndBought(make, false)) {
                carViews.add(modelMapper.map(car, CarView.class));
        }
        return carViews;
    }

    @Override
    public OutputJson addCar(CarBindingModel carBindingModel) {
        OutputJson outputJson = new OutputJson();
        Car car = modelMapper.map(carBindingModel, Car.class);
        carRepository.save(car);
        outputJson.setSuccess(true);
        outputJson.setMessage("Успешно добавихте автомобил " + carBindingModel.getMake());
        return outputJson;
    }

    @Override
    public void changePrice() {
        for (Car car : carRepository.getAllByBought(false)) {
            car.setPrice(car.getPrice().subtract(BigDecimal.valueOf(1000)));
            carRepository.save(car);
        }
    }
}
