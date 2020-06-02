package helpers;

public class PriceHelper {

    public static String getPriceFromString(String priceText) {
        // from 204.00 UAH - get 204

        return priceText.replaceAll("\\.00.*+", "").replaceAll(" ", "");
    }


    public static String getPriceAsString(String textPrice) {
        return textPrice.replaceAll("[A-z–+\\sА-я]", "")
                .replaceAll(" ", "");
    }




    public static String getPriceFromStringnoComa(String textPrice) {
        return textPrice.replaceAll("[^,.0-9]","")
                .replaceAll(" ", "").replaceAll(",", "");
    }


}