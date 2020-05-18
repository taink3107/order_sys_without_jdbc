package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OrderDetail {

    private List<ProductDetail> productDetailList;
    private long total;
    private Customer customer;

    public List<ProductDetail> getProductDetailList() {
        return productDetailList;
    }

    public OrderDetail(List<ProductDetail> productDetailList, Customer customer) {
        this.productDetailList = productDetailList;
        this.customer = customer;

        total = productDetailList.stream()
                .mapToLong(productDetail -> productDetail.getTotal())
                .sum();
    }

    public long getTotal() {
        return total;
    }

    public void generate(OutputStream out) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("./src/main/resources/orderTempl.html")));
        //System.out.println(content);

        String result = String.format(content, getProductListRow(productDetailList), total + "");
        try(PrintWriter writer = new PrintWriter(out)) {
            writer.print(result);
        };

    }

    public String getProductListRow(List<ProductDetail> productDetails) {
        //<tr><td>product name</td><td>price</td><tr>
        //<tr><td>product name</td><td>price</td><tr>
//        StringBuilder builder = new StringBuilder();
        return productDetails.stream().map(productDetail -> getProductRow(productDetail))
                 .reduce("", (x, y) -> x + y);
    }

    public String getProductRow(ProductDetail productDetail) {
        //<tr><td>product name</td><td>price</td><tr>
        return String.format("<tr><td>%s</td><td>%s</td><tr>",
                productDetail.getProduct().getName(),
                productDetail.getTotal() + "");
    }


}
