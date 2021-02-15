import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';
part 'PhysicalGoods.g.dart';

@JsonSerializable()
class PhysicalGoods {
  String physicalGoods;
  String productProfile;

  PhysicalGoods({@required this.productProfile, @required this.physicalGoods});
  Map<String, dynamic> toJson() => _$PhysicalGoodsToJson(this);
  factory PhysicalGoods.fromJson(Map<String, dynamic> json) => _$PhysicalGoodsFromJson(json);

  String getProductProfile() {
    return this.productProfile;
  }

  void setProductProfile(String productProfile) {
    this.productProfile = productProfile;
  }

  String getPhysicalGoods() {
    return this.physicalGoods;
  }

  void setPhysicalGoods(String physicalGoods) {
    this.physicalGoods = physicalGoods;
  }
}