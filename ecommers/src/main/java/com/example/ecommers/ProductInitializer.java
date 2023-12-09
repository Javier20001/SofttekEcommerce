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
            ProductEntity product = new ProductEntity(1L,"Logitech rx pro",4,"https://s3-sa-east-1.amazonaws.com/saasargentina/oaPmQNJPQeMZynN9AOk5/imagen","Sumérgete en el mundo de los videojuegos con el mouse gamer Logitech Pro Series G Pro Hero en color negro. Diseñado especialmente para gamers, este mouse cuenta con un sensor óptico Hero 25K de alta precisión que te permitirá apuntar y moverte con total fluidez y exactitud. Con sus 25600 dpi de resolución, no habrá enemigo que se te escape.",new BigDecimal(500),true,category);
            i_productRepository.save(product);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category2 = i_categoryRepository.findById(2L).orElseGet(() -> {
                CategoryEntity newCategory2 = new CategoryEntity(2L,"keyboard",true);
                return i_categoryRepository.save(newCategory2);
            });
            ProductEntity product2 = new ProductEntity(2L,"Razer elite v1",14,"https://www.trustedreviews.com/wp-content/uploads/sites/54/2021/07/Best-gaming-keyboard-920x613.jpg","Desde 2005 Razer es la marca líder de estilo de vida para jugadores. Ha diseñado y construido el ecosistema de hardware, software y servicios más grande del mundo. La ubicación de cada botón y curva de los mouses Razer se crearon para adaptarse perfectamente a tu mano. Gracias a estos dispositivos, conseguirás una experiencia de juego cómoda y placentera.",new BigDecimal(1500),true,category2);
            i_productRepository.save(product2);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category3 = i_categoryRepository.findById(3L).orElseGet(() -> {
                CategoryEntity newCategory3 = new CategoryEntity(3L,"RAM",true);
                return i_categoryRepository.save(newCategory3);
            });
            ProductEntity product3 = new ProductEntity(3L,"Kingston 8GB ddr4",10,"https://www.venex.com.ar/products_images/1599918773_8gb.jpg","Si tu computadora funciona con lentitud, si un programa no responde o no se carga, lo más probable es que se trate de un problema de memoria. Estos son posibles indicios de un rendimiento defectuoso en el día a día de tus tareas. Por ello, contar con una memoria Kingston -sinónimo de trayectoria y excelencia- mejorará la productividad de tu equipo: las páginas se cargarán más rápido y la ejecución de nuevas aplicaciones resultará más ágil y simple.",new BigDecimal(3500),true,category3);
            i_productRepository.save(product3);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category4 = i_categoryRepository.findById(4L).orElseGet(() -> {
                CategoryEntity newCategory4 = new CategoryEntity(4L,"CPU",true);
                return i_categoryRepository.save(newCategory4);
            });
            ProductEntity product4 = new ProductEntity(4L,"Intel i9-9900k",3,"https://app.contabilium.com/files/explorer/7026/Productos-Servicios/concepto-6303558.jpg","Productividad y entretenimiento, todo disponible en tu computadora de escritorio. La superioridad tecnológica de INTEL es un beneficio para todo tipo de profesionales. Asegura el mejor rendimiento de las aplicaciones, de la transferencia de datos y la conexión con otros elementos tecnológicos.",new BigDecimal(10500),true,category4);
            i_productRepository.save(product4);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category5 = i_categoryRepository.findById(5L).orElseGet(() -> {
                CategoryEntity newCategory5 = new CategoryEntity(5L,"GPU",true);
                return i_categoryRepository.save(newCategory5);
            });
            ProductEntity product5 = new ProductEntity(5L,"Nvidia Radeon 5550xt",1,"https://www.igorslab.de/wp-content/uploads/2020/01/Intro.jpg","Este componente electrónico procesa la información que llega al dispositivo y los transforma en imágenes o videos para mostrarla visualmente. Es ideal para trabajar con aplicaciones gráficas ya que permite obtener imágenes más nítidas.", new BigDecimal(13500),true,category5);
            i_productRepository.save(product5);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category6 = i_categoryRepository.findById(6L).orElseGet(() -> {
                CategoryEntity newCategory6 = new CategoryEntity(6L,"monitor",true);
                return i_categoryRepository.save(newCategory6);
            });
            ProductEntity product6 = new ProductEntity(6L,"Asus ultra HD",2,"https://img.freepik.com/free-photo/view-computer-monitor-with-gradient-display_23-2150757379.jpg", "Con sus 27\" y una resolución de 1920psx x 1080px te brindará imágenes nítidas y colores vibrantes para una experiencia visual envolvente. Además viene con 2 puertos HDMI 1.4 y 1 DP 1.2",new BigDecimal(12500),true,category6);
            i_productRepository.save(product6);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category7 = i_categoryRepository.findById(7L).orElseGet(() -> {
                CategoryEntity newCategory7 = new CategoryEntity(7L,"motherboard",true);
                return i_categoryRepository.save(newCategory7);
            });
            ProductEntity product7 = new ProductEntity(7L,"Msi a320m pro",2,"https://nutnullpc.com/images/thumbs/0001604_msi-a320m-pro-e-motherboard.png","Socket Intel LGA 1700: Soporta procesadores de 13ª y próxima generaciónDDR5 de doble canal: 4* DIMM con soporte de módulo de memoria XMP 3.0 Diseño de potencia dominante: Solución VRM directa digital de 20+1+2 fases Diseño térmico de vanguardia: Armadura térmica VRM Aletas Array y M.2 Thermal Guard Almacenamiento ultrarrápido: 5* M .2 ranuras, Incluye 1* PCIe 5.0 x4Fast Networking: LAN de 10 GbE y Wi-Fi 7 Conectividad extendida: PCIe 5.0, DP, frontal y doble posterior USB-C 20Gb/sSfinición fina: RGB FUSION 2.0", new BigDecimal(5500),true,category7);
            i_productRepository.save(product7);
        }
    }
}