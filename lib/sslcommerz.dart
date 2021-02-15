import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:sslcommerz_flutter/model/SSLCEMITransactionInitializer.dart';
import 'package:sslcommerz_flutter/model/SSLCShipmentInfoInitializer.dart';
import 'package:sslcommerz_flutter/model/SSLCTransactionInfoModel.dart';
import 'package:sslcommerz_flutter/model/SSLCommerzInitialization.dart';
import 'package:sslcommerz_flutter/model/sslproductinitilizer/SSLCProductInitializer.dart';

import 'model/SSLCAdditionalInitializer.dart';
import 'model/SSLCCustomerInfoInitializer.dart';

part 'sslcommerz.g.dart';

@JsonSerializable()
class Sslcommerz {
  SSLCommerzInitialization initializer;
  SSLCCustomerInfoInitializer customerInfoInitializer;
  SSLCEMITransactionInitializer sslcemiTransactionInitializer;
  SSLCShipmentInfoInitializer sslcShipmentInfoInitializer;
  SSLCProductInitializer sslcProductInitializer;
  SSLCAdditionalInitializer sslcAdditionalInitializer;

  Sslcommerz({@required SSLCommerzInitialization initializer}) {
    this.initializer = initializer;
  }

  static const MethodChannel _channel = const MethodChannel('sslcommerz');

  Map<String, dynamic> toJson() => _$SslcommerzToJson(this);

  factory Sslcommerz.fromJson(Map<String, dynamic> json) =>
      _$SslcommerzFromJson(json);

  Sslcommerz addCustomerInfoInitializer(
      {@required SSLCCustomerInfoInitializer customerInfoInitializer}) {
    this.customerInfoInitializer = customerInfoInitializer;
    return this;
  }

  Sslcommerz addEMITransactionInitializer(
      {@required SSLCEMITransactionInitializer sslcemiTransactionInitializer}) {
    this.sslcemiTransactionInitializer = sslcemiTransactionInitializer;
    return this;
  }

  Sslcommerz addShipmentInfoInitializer(
      {@required SSLCShipmentInfoInitializer sslcShipmentInfoInitializer}) {
    this.sslcShipmentInfoInitializer = sslcShipmentInfoInitializer;
    return this;
  }

  Sslcommerz addProductInitializer(
      {@required SSLCProductInitializer sslcProductInitializer}) {
    this.sslcProductInitializer = sslcProductInitializer;
    return this;
  }

  Sslcommerz addAdditionalInitializer(
      {@required SSLCAdditionalInitializer sslcAdditionalInitializer}) {
    this.sslcAdditionalInitializer = sslcAdditionalInitializer;
  }

  Future<dynamic> payNow() async {
    try {
      var response =
          await _channel.invokeMethod('initiateSSLCommerz', jsonEncode(this));
      return SSLCTransactionInfoModel.fromJson(jsonDecode(response));
    } on PlatformException catch (e) {
      return e;
    }
  }
}
