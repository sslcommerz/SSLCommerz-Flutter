import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';
part 'NonPhysicalGoods.g.dart';

@JsonSerializable()
class NonPhysicalGoods {
  String nonPhysicalGoods;
  String productProfile;

  NonPhysicalGoods({@required this.productProfile, @required this.nonPhysicalGoods});
  Map<String, dynamic> toJson() => _$NonPhysicalGoodsToJson(this);
  factory NonPhysicalGoods.fromJson(Map<String, dynamic> json) => _$NonPhysicalGoodsFromJson(json);

  String getProductProfile() {
    return this.productProfile;
  }

  void setProductProfile(String productProfile) {
    this.productProfile = productProfile;
  }

  String getNonPhysicalGoods() {
    return this.nonPhysicalGoods;
  }

  void setNonPhysicalGoods(String nonPhysicalGoods) {
    this.nonPhysicalGoods = nonPhysicalGoods;
  }
}