package Nobilities.ex36;

import java.util.Collection;

public class BarcodeScanner {
    private final Collection<Product> products;
    public BarcodeScanner(Collection<Product> products) {
        this.products = products;
    }

    public Priceable scan(Barcode barcode) {
        if (barcode.code().isEmpty()) {
            return new Priceable.Empty();
        }

        for(final var product : products) {
            if (product.barcode().equals(barcode)) {
                return product.price();
            }
        }

//        if (barcode.equals(new Barcode("99999"))) {
//            return new Priceable.NotFound();
//        }

        return new Priceable.NotFound();
    }

    public double scanAndSum(Collection<Barcode> items) {
        double sum = 0.0;
        for(final var item : items) {
            final var price = scan(item);
            if (price instanceof Priceable.Price p) {
                sum += p.amount();
            }
        }

        return sum;
    }
}
