// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'TravelVertical.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

TravelVertical _$TravelVerticalFromJson(Map<String, dynamic> json) {
  return TravelVertical(
    productProfile: json['productProfile'] as String,
    hotelName: json['hotelName'] as String,
    lengthOfStay: json['lengthOfStay'] as String,
    checkInTime: json['checkInTime'] as String,
    hotelCity: json['hotelCity'] as String,
  );
}

Map<String, dynamic> _$TravelVerticalToJson(TravelVertical instance) =>
    <String, dynamic>{
      'productProfile': instance.productProfile,
      'hotelName': instance.hotelName,
      'lengthOfStay': instance.lengthOfStay,
      'checkInTime': instance.checkInTime,
      'hotelCity': instance.hotelCity,
    };
