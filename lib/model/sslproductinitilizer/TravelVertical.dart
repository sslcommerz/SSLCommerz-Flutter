import 'package:flutter/material.dart';
import 'package:json_annotation/json_annotation.dart';

part 'TravelVertical.g.dart';

@JsonSerializable()
class TravelVertical {
  String productProfile;
  String hotelName;
  String lengthOfStay;
  String checkInTime;
  String hotelCity;

  TravelVertical(
      {@required this.productProfile,
      @required this.hotelName,
      @required this.lengthOfStay,
      @required this.checkInTime,
      @required this.hotelCity});

  Map<String, dynamic> toJson() => _$TravelVerticalToJson(this);

  factory TravelVertical.fromJson(Map<String, dynamic> json) =>
      _$TravelVerticalFromJson(json);

  String getProductProfile() {
    return this.productProfile;
  }

  void setProductProfile(String productProfile) {
    this.productProfile = productProfile;
  }

  String getHotelName() {
    return this.hotelName;
  }

  void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  String getLengthOfStay() {
    return this.lengthOfStay;
  }

  void setLengthOfStay(String lengthOfStay) {
    this.lengthOfStay = lengthOfStay;
  }

  String getCheckInTime() {
    return this.checkInTime;
  }

  void setCheckInTime(String checkInTime) {
    this.checkInTime = checkInTime;
  }

  String getHotelCity() {
    return this.hotelCity;
  }

  void setHotelCity(String hotelCity) {
    this.hotelCity = hotelCity;
  }
}
