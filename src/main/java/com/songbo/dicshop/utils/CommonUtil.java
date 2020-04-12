package com.songbo.dicshop.utils;

/**
 * @ClassName CommonUtil
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/12 下午5:22
 **/

public class CommonUtil {

   // public final static String IMAGESCDN = "https://172.104.191.125:9999/var/www/html/dicshopimgs/";
    public final static String IMAGESCDN = "https://172.104.191.125:9999/var/www/html/dicshopimgs/";

    public final static String IMAGEURL = "/home/songbo/Pictures/";


    //header
    public final static String HEADER_TOKEN = "TOKEN";
    public final static String HEADER_USER = "userId";

    public final static String NODE_LOGIN = "user2551474857";
    public final static String NODE_PASS = "passec2dcfcd4940a115ee57c7e0176d51a430a62377922c9066a2aff35fbe1f760706";
    public final static String NODE_ADDRESS = "http://172.104.191.125:21331";
    //public final static String NODE_ADDRESS_STANDBY = "http://172.104.191.125:48903";

    //public final static double DIC_FEE = 0.00005;
    public final static double DIC_FEE = 0.00164;
    //explorer
    public static String EXPLORER_URL1 = "http://explorer.indexchain.io/";
    //public static String EXPLORER_URL2 = "http://172.104.191.125:48906/";

    public static String EXPLORER_URL_addr = "insight-api-komodo/addr/";
    public static String EXPLORER_URL_addrs = "insight-api-komodo/addrs/";
    public static String EXPLORER_URL_balance = "/balance";
    public static String EXPLORER_URL_utxo = "/utxo";
    //https://explorer.indexchain.io/insight-api-komodo/addrs/RGd77ChrgHVNgYU4ptXaofCCBYnkho1xJr/txs?from=0&to=10
    public static String EXPLORER_URL_txs = "/txs?";

    public final static String SENDTOADDRESS = "sendtoaddress";
    public final static String CREATE_ADDRESS = "getnewaddress";
    public final static String GET_BALANCE = "getaddressbalance";
    public final static String GET_UTXOS = "getaddressutxos";



    //imgs
    public final static String IMGS_URL = "/home/dicmall/imgs";
    //public final static String IMGS_URL = "/home/songbo/Pictures";


    //nowpayments
    public final static String NOWPAYMENTS_URL = "https://api.nowpayments.io/v1/";
    public final static String NOWPAYMENTS_HEADER_KEY = "x-api-key";
    public final static String NOWPAYMENTS_HEADER_VALUE = "VYBM29V-V3TMV7H-KQE0MSK-HYM40PQ";

    //coinpayments
    public final static String COINPAYMENTS_URL = "https://www.coinpayments.net/api.php";
    public final static String COINPAYMENTS_CREATE_TRANSACTION = "create_transaction";
    public final static String COINPAYMENTS_GET_TX_INFO = "get_tx_info";
    public final static String COINPAYMENTS_ADDRESS = "0xD1C3Fd452e9976DbaFBA4E0365bEE7aBD614a4dC";
    public final static String COINPAYMENTS_MAIL = "1362718167@qq.com";

    public final static String COINPAYMENTS_HEADER_TYPE_KEY = "Content-Type";
    public final static String COINPAYMENTS_HEADER_RYPE_VALUE = "application/x-www-form-urlencoded";
    public final static String COINPAYMENTS_HEADER_HMAC = "HMAC";

    public final static String COINPAYMENTS_PUBLIC_KEY = "bc4146d37bbeb0cd6c29833c951f11444f068bd351da02290409eb580b82e565";
    //version=1&cmd=rates&key=bc4146d37bbeb0cd6c29833c951f11444f068bd351da02290409eb580b82e565&format=json
    public final static String COINPAYMENTS_PUBLIC_DATA = "version=1&key=bc4146d37bbeb0cd6c29833c951f11444f068bd351da02290409eb580b82e565&format=json";
    public final static String COINPAYMENTS_PRIVATE_KEY = "6f106fbc15c9c09811c5d14d7FdAC71313a7B1B514e3428F03aF12398C3544d5";
}
