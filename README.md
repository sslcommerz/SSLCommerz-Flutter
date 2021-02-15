# SSLCommerz - Flutter

** SSLCommerz flutter SDK for Android. `Use this package as a library`.

## Resource

* Package URL: https://pub.dev/packages/sslcommerz_flutter
* Versions: 0.0.3
    * Release Date - [Nov 8, 2020]
    * Change Log - Bug Fixed
    

## Installation Steps

1. Depend on it

Add this to your package's **pubspec.yaml** file:
```
dependencies:
    sslcommerz_flutter: ^0.0.3
```

2. Install it

You can install packages from the command line with Flutter:
```
$ flutter pub get
```

Alternatively, your editor might support `flutter pub get`. Check the docs for your editor to learn more.

3. Import it
    
Now in your Dart code, you can use:
```
import 'package:sslcommerz_flutter/model/SSLCAdditionalInitializer.dart';
import 'package:sslcommerz_flutter/model/SSLCAdditionalInitializer.g.dart';
import 'package:sslcommerz_flutter/model/SSLCCustomerInfoInitializer.dart';
import 'package:sslcommerz_flutter/model/SSLCCustomerInfoInitializer.g.dart';
import 'package:sslcommerz_flutter/model/SSLCEMITransactionInitializer.dart';
import 'package:sslcommerz_flutter/model/SSLCEMITransactionInitializer.g.dart';
import 'package:sslcommerz_flutter/model/SSLCommerzInitialization.dart';
import 'package:sslcommerz_flutter/model/SSLCommerzInitialization.g.dart';
import 'package:sslcommerz_flutter/model/SSLCSdkType.dart';
import 'package:sslcommerz_flutter/model/SSLCShipmentInfoInitializer.dart';
import 'package:sslcommerz_flutter/model/SSLCShipmentInfoInitializer.g.dart';
import 'package:sslcommerz_flutter/model/SSLCTransactionInfoModel.dart';
import 'package:sslcommerz_flutter/model/SSLCurrencyType.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/AirlinesTicket.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/AirlinesTicket.g.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/General.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/General.g.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/NonPhysicalGoods.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/NonPhysicalGoods.g.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/PhysicalGoods.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/PhysicalGoods.g.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/SSLCProductInitializer.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/SSLCProductInitializer.g.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/TelecomVertical.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/TelecomVertical.g.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/TravelVertical.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/TravelVertical.g.dart';
import 'package:sslcommerz_flutter/sslcommerz.dart';
import 'package:sslcommerz_flutter/sslcommerz.g.dart';
```

4. Deployment

Add this below code to your `app's build gradle`. After modifying the `app's build gradle`, please clean the app by the command `flutter clean` then build the release apk again.

```
buildTypes {
    release {
        // .................. your existing codes ..................
        // add these three lines
        
        shrinkResources false
        zipAlignEnabled false
        minifyEnabled false
        }
    }
```


## Example

1. example/lib/main.dart

```
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:sslcommerz_flutter/model/SSLCAdditionalInitializer.dart';
import 'package:sslcommerz_flutter/model/SSLCCustomerInfoInitializer.dart';
import 'package:sslcommerz_flutter/model/SSLCEMITransactionInitializer.dart';
import 'package:sslcommerz_flutter/model/SSLCSdkType.dart';
import 'package:sslcommerz_flutter/model/SSLCShipmentInfoInitializer.dart';
import 'package:sslcommerz_flutter/model/SSLCTransactionInfoModel.dart';
import 'package:sslcommerz_flutter/model/SSLCommerzInitialization.dart';
import 'package:sslcommerz_flutter/model/SSLCurrencyType.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/General.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/SSLCProductInitializer.dart';
import 'package:sslcommerz_flutter/sslcommerz.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  var _key = GlobalKey<FormState>();
  dynamic formData = {};

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: const Text('SSLCommerz'),
          ),
          body: Form(
            key: _key,
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: SingleChildScrollView(
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: TextFormField(
                        initialValue: "testbox",
                        keyboardType: TextInputType.text,
                        decoration: InputDecoration(
                          border: OutlineInputBorder(
                              borderRadius:
                                  BorderRadius.all(Radius.circular(8.0))),
                          hintText: "Store ID",
                        ),
                        validator: (value) {
                          if (value.isEmpty)
                            return "Please input store id";
                          else
                            return null;
                        },
                        onSaved: (value) {
                          formData['store_id'] = value;
                        },
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: TextFormField(
                        initialValue: "qwerty",
                        keyboardType: TextInputType.text,
                        decoration: InputDecoration(
                          border: OutlineInputBorder(
                              borderRadius:
                                  BorderRadius.all(Radius.circular(8.0))),
                          hintText: "Store password",
                        ),
                        validator: (value) {
                          if (value.isEmpty)
                            return "Please input store password";
                          else
                            return null;
                        },
                        onSaved: (value) {
                          formData['store_password'] = value;
                        },
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: TextFormField(
                        keyboardType: TextInputType.phone,
                        decoration: InputDecoration(
                          border: OutlineInputBorder(
                              borderRadius:
                                  BorderRadius.all(Radius.circular(8.0))),
                          hintText: "Phone number",
                        ),
                        onSaved: (value) {
                          formData['phone'] = value;
                        },
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: TextFormField(
                        initialValue: "10",
                        keyboardType: TextInputType.number,
                        decoration: InputDecoration(
                          border: OutlineInputBorder(
                              borderRadius:
                                  BorderRadius.all(Radius.circular(8.0))),
                          hintText: "Payment amount",
                        ),
                        validator: (value) {
                          if (value.isEmpty)
                            return "Please input amount";
                          else
                            return null;
                        },
                        onSaved: (value) {
                          formData['amount'] = double.parse(value);
                        },
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: TextFormField(
                        keyboardType: TextInputType.text,
                        decoration: InputDecoration(
                          border: OutlineInputBorder(
                              borderRadius:
                                  BorderRadius.all(Radius.circular(8.0))),
                          hintText: "Enter multi card",
                        ),
                        onSaved: (value) {
                          formData['multicard'] = value;
                        },
                      ),
                    ),
                    RaisedButton(
                      child: Text("Pay now"),
                      onPressed: () {
                        if (_key.currentState.validate()) {
                          _key.currentState.save();
                          // sslCommerzGeneralCall();
                          sslCommerzCustomizedCall();
                        }
                      },
                    )
                  ],
                ),
              ),
            ),
          )),
    );
  }

  Future<void> sslCommerzGeneralCall() async {
    Sslcommerz sslcommerz = Sslcommerz(
        initializer: SSLCommerzInitialization(
            ipn_url: "www.ipnurl.com",
            multi_card_name: formData['multicard'],
            currency: SSLCurrencyType.BDT,
            product_category: "Food",
            sdkType: SSLCSdkType.TESTBOX,
            store_id: formData['store_id'],
            store_passwd: formData['store_password'],
            total_amount: formData['amount'],
            tran_id: "1231321321321312"));
    sslcommerz.payNow();
  }

  Future<void> sslCommerzCustomizedCall() async {
    Sslcommerz sslcommerz = Sslcommerz(
        initializer: SSLCommerzInitialization(
            ipn_url: "www.ipnurl.com",
            multi_card_name: formData['multicard'],
            currency: SSLCurrencyType.BDT,
            product_category: "Food",
            sdkType: SSLCSdkType.TESTBOX,
            store_id: formData['store_id'],
            store_passwd: formData['store_password'],
            total_amount: formData['amount'],
            tran_id: "1231321321321312"));
    sslcommerz
        .addEMITransactionInitializer(
            sslcemiTransactionInitializer: SSLCEMITransactionInitializer(
                emi_options: 1, emi_max_list_options: 3, emi_selected_inst: 2))
        .addShipmentInfoInitializer(
            sslcShipmentInfoInitializer: SSLCShipmentInfoInitializer(
                shipmentMethod: "yes",
                numOfItems: 5,
                shipmentDetails: ShipmentDetails(
                    shipAddress1: "Ship address 1",
                    shipCity: "Faridpur",
                    shipCountry: "Bangladesh",
                    shipName: "Ship name 1",
                    shipPostCode: "7860")))
        .addCustomerInfoInitializer(
            customerInfoInitializer: SSLCCustomerInfoInitializer(
                customerName: null,
                customerEmail: null,
                customerAddress1: null,
                customerCity: null,
                customerPostCode: null,
                customerCountry: null,
                customerPhone: formData['phone']))
        .addProductInitializer(
            sslcProductInitializer:
                // ***** ssl product initializer for general product STARTS*****
                SSLCProductInitializer(
                    productName: "Water Filter",
                    productCategory: "Widgets",
                    general: General(
                        general: "General Purpose",
                        productProfile: "Product Profile"))
            // ***** ssl product initializer for general product ENDS*****

            // ***** ssl product initializer for non physical goods STARTS *****
            // SSLCProductInitializer.WithNonPhysicalGoodsProfile(
            //     productName:
            //   "productName",
            //   productCategory:
            //   "productCategory",
            //   nonPhysicalGoods:
            //   NonPhysicalGoods(
            //      productProfile:
            //       "Product profile",
            //     nonPhysicalGoods:
            //     "non physical good"
            //       ))
            // ***** ssl product initializer for non physical goods ENDS *****

            // ***** ssl product initialization for travel vertices STARTS *****
            //       SSLCProductInitializer.WithTravelVerticalProfile(
            //          productName:
            //         "productName",
            //         productCategory:
            //         "productCategory",
            //         travelVertical:
            //         TravelVertical(
            //               productProfile: "productProfile",
            //               hotelName: "hotelName",
            //               lengthOfStay: "lengthOfStay",
            //               checkInTime: "checkInTime",
            //               hotelCity: "hotelCity"
            //             )
            //       )
            // ***** ssl product initialization for travel vertices ENDS *****

            // ***** ssl product initialization for physical goods STARTS *****

            // SSLCProductInitializer.WithPhysicalGoodsProfile(
            //     productName: "productName",
            //     productCategory: "productCategory",
            //     physicalGoods: PhysicalGoods(
            //         productProfile: "Product profile",
            //         physicalGoods: "non physical good"))

            // ***** ssl product initialization for physical goods ENDS *****

            // ***** ssl product initialization for telecom vertice STARTS *****
            // SSLCProductInitializer.WithTelecomVerticalProfile(
            //     productName: "productName",
            //     productCategory: "productCategory",
            //     telecomVertical: TelecomVertical(
            //         productProfile: "productProfile",
            //         productType: "productType",
            //         topUpNumber: "topUpNumber",
            //         countryTopUp: "countryTopUp"))
            // ***** ssl product initialization for telecom vertice ENDS *****
            )
        .addAdditionalInitializer(
            sslcAdditionalInitializer: SSLCAdditionalInitializer(
                valueA: "value a ",
                valueB: "value b",
                valueC: "value c",
                valueD: "value d"));
        var result = await sslcommerz.payNow();
        if (result is PlatformException) {
          print("the response is: " + result.message + " code: " + result.code);
        } else {
          SSLCTransactionInfoModel model = result;
        }
    }
}
```

2. Success Listener

Success callback return below json data at `SSLCTransactionInfoModel model`. Grab data from the model.

`aPIConnect`,`amount`,`bankTranId`,`baseFair`,`cardBrand`,`cardIssuer`,`cardIssuerCountry`,`cardIssuerCountryCode`,`cardNo`,`cardType`,`currencyAmount`,`currencyRate`,`currencyType`,`gwVersion`,`riskLevel`,`riskTitle`,`sessionkey`,`status`,`storeAmount`,`tranDate`,`tranId`,`valId`,`validatedOn`,`valueA`,`valueB`,`valueC`,`valueD`


For any issue, feel free to contact any time.

- Author : SSL Wireless Mobile Team
- Maintain : SSLCommerz Integration Team
- Team-Email: integration@sslcommerz.com 
- More info: https://sslcommerz.com

© 2020 SSLCOMMERZ ALL RIGHTS RESERVED