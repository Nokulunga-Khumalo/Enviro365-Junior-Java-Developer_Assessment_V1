package com.example.demo.Consumer;


import com.example.demo.Product.Product;
//import com.example.demo.Product.ProductDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.WithdrawalNotice.WithdrawalNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@SpringBootApplication
public class ConsumerService {

// calls out methods that retrieve the investor details and products list

        @Autowired
        private ConsumerRepository investorRepository;

        @Autowired
        private Product product;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private WithdrawalNoticeRepository withdrawalNoticeRepository;

    public ConsumerService(ConsumerRepository investorRepository, Product productDTO, ProductRepository productRepository, WithdrawalNoticeRepository withdrawalNoticeRepository) {
        this.investorRepository = investorRepository;
        this.product = productDTO;
        this.productRepository = productRepository;
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
    }

    public Investor getInvestorDetails(String email) throws Throwable {
         Investor new_investor= new Investor();

            Optional<Investor> optionalInvestor = investorRepository. findByEmail(email);

            if (optionalInvestor.isPresent()) {

                Investor investor = optionalInvestor.get();
                List<Product> products = getProductsForInvestor(email);
                investor.setProducts(products);
                return investor;
            } else {
                addNewInvestor(new_investor, email);
                throw new Throwable ("Investor not found for this email: " + email);
            }
        }

        public List<Product> getProductsForInvestor(String email) {

            Optional<Product> products = productRepository.findByEmail(email);
            return products.stream()
                        .map(this::convertToProductData)
                        .collect(Collectors.toList());
        }

    private List<Investor> convertToInvestorData(List<Investor> investors, List<Product> products) {
        return investors.stream()
                .map(investor -> {

                    investor.setFirstName(investor.getFirstName());
                    investor.setLastName(investor.getLastName());
                    investor.setAddress(investor.getAddress());
                    investor.setEmail(investor.getEmail());
                    investor.setContacts(investor.getContacts());
                    investor.setAge(investor.getAge());
                    investor.setProducts(products);
                    return investor;
                })
                .collect(Collectors.toList());
    }

        private Product convertToProductData(Product product) {

            product.setId(product.getId());
            product.setType(product.getType());
            product.setName(product.getName());
            product.setCurrentBalance(product.getCurrentBalance());

            return product;
        }

        public void addNewInvestor(Investor newInvestor, String email){

            newInvestor.setEmail(email);

            investorRepository.save(newInvestor);
            System.out.println(newInvestor);

        }
        public static void main(String[] args) {
            SpringApplication.run(ConsumerService.class,args);
        }

}
