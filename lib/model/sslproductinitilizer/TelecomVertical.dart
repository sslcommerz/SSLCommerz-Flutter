import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';

part 'TelecomVertical.g.dart';

@JsonSerializable()
class TelecomVertical {
  String productProfile;
  String productType;
  String topUpNumber;
  String countryTopUp;

  TelecomVertical(
      {@required this.productProfile,
      @required this.productType,
      @required this.topUpNumber,
      @required this.countryTopUp});

  Map<String, dynamic> toJson() => _$TelecomVerticalToJson(this);

  factory TelecomVertical.fromJson(Map<String, dynamic> json) =>
      _$TelecomVerticalFromJson(json);

  String getProductProfile() {
    return this.productProfile;
  }

  void setProductProfile(String productProfile) {
    this.productProfile = productProfile;
  }

  String getProductType() {
    return this.productType;
  }

  void setProductType(String productType) {
    this.productType = productType;
  }

  String getTopUpNumber() {
    return this.topUpNumber;
  }

  void setTopUpNumber(String topUpNumber) {
    this.topUpNumber = topUpNumber;
  }

  String getCountryTopUp() {
    return this.countryTopUp;
  }

  void setCountryTopUp(String countryTopUp) {
    this.countryTopUp = countryTopUp;
  }
}
