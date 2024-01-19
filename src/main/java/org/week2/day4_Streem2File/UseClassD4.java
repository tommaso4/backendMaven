package org.week2.day4_Streem2File;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UseClassD4 {
    public static void main(String[] args) {

        Product product1 = new Product(1L, "King Of the  rings1", "Books", 22.0);
        Product product2 = new Product(2L, "King Of the  rings2", "Books", 125.0);
        Product product3 = new Product(3L, "Cappucceto Rosso1", "Baby", 12.0);
        Product product4 = new Product(4L, "Cappucceto Rosso2", "Baby", 15.0);
        Product product5 = new Product(5L, "Storia Medievale1", "Boys", 35.0);
        Product product6 = new Product(6L, "Storia Medievale2", "Boys", 31.0);

        List<Product> productList = List.of(product1, product6, product3, product4, product5, product2);

        Costumer costumer1 = new Costumer(1L, "Mario Rossi", 2);
        Costumer costumer2 = new Costumer(2L, "Mario Bianchi", 2);
        Costumer costumer3 = new Costumer(3L, "Mario Verdi", 1);

        Order order1 = new Order(1L, "Building", productList, costumer1, LocalDate.of(2021, 01, 01));
        Order order2 = new Order(2L, "Building", productList, costumer2, LocalDate.of(2021, 02, 04));
        Order order3 = new Order(3L, "Building", productList, costumer3, LocalDate.of(2021, 01, 01));
        Order order4 = new Order(4L, "Building", productList, costumer3, LocalDate.of(2021, 01, 01));

        List<Order> orderList = List.of(order1, order2, order3, order4);

        System.out.println("es1");
        Map<Costumer, List<Order>> costumerListMap =
                orderList.stream().collect(
                        Collectors.groupingBy(Order::getCostumer, Collectors.toList()));

        // Stampa la mappa risultante
        costumerListMap.forEach((costumer, orders) -> {
            System.out.println("Cliente: " + costumer.getName());
            System.out.println("Ordini:");
            orders.forEach(System.out::println);
            System.out.println("--------");
        });

        System.out.println("es2");
        Map<Costumer, Double> totalCostByCostumer =
                orderList.stream().collect(Collectors
                        .groupingBy(Order::getCostumer, Collectors.summingDouble(order -> order.getProducts()
                                .stream().mapToDouble(Product::getPrice).sum())));

        totalCostByCostumer.forEach((costurmer, totalCart) -> {
            System.out.println("Cliente: " + costurmer.getName() + " Totale Ordini: " + totalCart);
        });
        //ottengo il numero maggiore
        System.out.println("Es3");
        Double maxPriceOfList = productList.stream()
                .map(Product::getPrice)
                .collect(Collectors.maxBy(Comparator.naturalOrder())).get();
        System.out.println(maxPriceOfList);
        //ottengo i tre numeri più grandi
        List<Double> max3price = productList.stream()
                .map(Product::getPrice)
                .sorted(Comparator.reverseOrder())
                .limit(3).toList();
        System.out.println(max3price);

        System.out.println("Es4");
        Map<Order, Double> mediaOrderMap = orderList.stream().collect(Collectors.toMap(
                order -> order,
                media -> media.getProducts().stream().mapToDouble(Product::getPrice).average().getAsDouble()));

        mediaOrderMap.forEach((order, media) -> {
            System.out.println("Id:" + order.getId() + " media: " + media);
        });

        System.out.println("Es5");
        Map<String, Double> averageForCategory = productList.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));

        averageForCategory.forEach((cat, average) -> {
            System.out.println("categoria: " + cat + " media: " + average);
        });

        //Es6:

        saveProducts(productList);

    }

    public static void saveProducts(List<Product> list) {
        File file2 = new File("filepath/day4.txt");
        String myStr = "";
        for (Product s : list) {
            myStr += s.toString();
        }

        try {
            FileUtils.writeStringToFile(file2, myStr, Charset.defaultCharset(), true);

        } catch (IOException e) {
            e.getMessage();
        }
    }

}
