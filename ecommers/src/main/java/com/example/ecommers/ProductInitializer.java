package com.example.ecommers;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.repository.I_CategoryRepository;
import com.example.ecommers.repository.I_ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ProductInitializer implements ApplicationRunner {
    private final I_ProductRepository i_productRepository;
    private final I_CategoryRepository i_categoryRepository;


    //creation of products and categories
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (i_productRepository.count() == 0) {
            CategoryEntity category = i_categoryRepository.findById(1L).orElseGet(() -> {
                CategoryEntity newCategory = new CategoryEntity(1L,"mouse",true);
                return i_categoryRepository.save(newCategory);
            });
            ProductEntity product = new ProductEntity(1L,"Logitech rx pro",4,"https://s3-sa-east-1.amazonaws.com/saasargentina/oaPmQNJPQeMZynN9AOk5/imagen","Immerse yourself in the world of gaming with the Logitech Pro Series G Pro Hero gaming mouse in black. Specially designed for gamers, this mouse features a high-precision Hero 25K optical sensor that allows you to aim and move with total fluidity and accuracy. With its 25,600 dpi resolution, no enemy will escape your precision",new BigDecimal(500),true,category);
            i_productRepository.save(product);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category2 = i_categoryRepository.findById(2L).orElseGet(() -> {
                CategoryEntity newCategory2 = new CategoryEntity(2L,"keyboard",true);
                return i_categoryRepository.save(newCategory2);
            });
            ProductEntity product2 = new ProductEntity(2L,"Razer elite v1",14,"https://www.trustedreviews.com/wp-content/uploads/sites/54/2021/07/Best-gaming-keyboard-920x613.jpg","Since 2005, Razer has been the leading lifestyle brand for gamers. It has designed and built the world's largest ecosystem of hardware, software, and services. The placement of each button and the curve of Razer mice are created to fit seamlessly in your hand. Thanks to these devices, you'll achieve a comfortable and enjoyable gaming experience",new BigDecimal(1500),true,category2);
            i_productRepository.save(product2);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category3 = i_categoryRepository.findById(3L).orElseGet(() -> {
                CategoryEntity newCategory3 = new CategoryEntity(3L,"RAM",true);
                return i_categoryRepository.save(newCategory3);
            });
            ProductEntity product3 = new ProductEntity(3L,"Kingston 8GB ddr4",10,"https://www.venex.com.ar/products_images/1599918773_8gb.jpg","If your computer is running slowly, if a program is unresponsive, or if it fails to load, it's likely a memory issue. These are possible signs of faulty performance in your day-to-day tasks. Therefore, having Kingston memory – synonymous with reliability and excellence – will enhance your computer's productivity: pages will load faster, and the execution of new applications will be more efficient and straightforward",new BigDecimal(3500),true,category3);
            i_productRepository.save(product3);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category4 = i_categoryRepository.findById(4L).orElseGet(() -> {
                CategoryEntity newCategory4 = new CategoryEntity(4L,"CPU",true);
                return i_categoryRepository.save(newCategory4);
            });
            ProductEntity product4 = new ProductEntity(4L,"Intel i9-9900k",3,"https://app.contabilium.com/files/explorer/7026/Productos-Servicios/concepto-6303558.jpg","Productivity and entertainment, all available on your desktop computer. The technological superiority of INTEL is a benefit for all types of professionals. It ensures the best performance of applications, data transfer, and connectivity with other technological elements",new BigDecimal(10500),true,category4);
            i_productRepository.save(product4);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category5 = i_categoryRepository.findById(5L).orElseGet(() -> {
                CategoryEntity newCategory5 = new CategoryEntity(5L,"GPU",true);
                return i_categoryRepository.save(newCategory5);
            });
            ProductEntity product5 = new ProductEntity(5L,"Nvidia Radeon 5550xt",1,"https://www.igorslab.de/wp-content/uploads/2020/01/Intro.jpg","This electronic component processes the information that arrives at the device and transforms it into images or videos for visual display. It is ideal for working with graphic applications as it allows for sharper images", new BigDecimal(13500),true,category5);
            i_productRepository.save(product5);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category6 = i_categoryRepository.findById(6L).orElseGet(() -> {
                CategoryEntity newCategory6 = new CategoryEntity(6L,"monitor",true);
                return i_categoryRepository.save(newCategory6);
            });
            ProductEntity product6 = new ProductEntity(6L,"Asus ultra HD",2,"https://img.freepik.com/free-photo/view-computer-monitor-with-gradient-display_23-2150757379.jpg", "With its 27\" size and a resolution of 1920px x 1080px, it will provide you with sharp images and vibrant colors for an immersive visual experience. Additionally, it comes with 2 HDMI 1.4 ports and 1 DP 1.2",new BigDecimal(12500),true,category6);
            i_productRepository.save(product6);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category7 = i_categoryRepository.findById(7L).orElseGet(() -> {
                CategoryEntity newCategory7 = new CategoryEntity(7L,"motherboard",true);
                return i_categoryRepository.save(newCategory7);
            });
            ProductEntity product7 = new ProductEntity(7L,"Msi a320m pro",2,"https://nutnullpc.com/images/thumbs/0001604_msi-a320m-pro-e-motherboard.png","Intel LGA 1700 Socket: Supports 13th and next-generation processors Double-channel DDR5: 4* DIMM with XMP 3.0 memory module support Dominant power design: 20+1+2 phase direct digital VRM solution Cutting-edge thermal design: VRM Thermal Armor, Array Fins, and M.2 Thermal Guard Ultra-fast storage: 5* M.2 slots, includes 1* PCIe 5.0 x4 Fast Networking: 10GbE LAN and Wi-Fi 7 Extended connectivity: PCIe 5.0, DP, front and dual rear USB-C 20Gb/s Fine definition: RGB FUSION 2.0", new BigDecimal(5500),true,category7);
            i_productRepository.save(product7);
        }
    }
}