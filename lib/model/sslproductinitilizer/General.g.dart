// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'General.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

General _$GeneralFromJson(Map<String, dynamic> json) {
  return General(
    productProfile: json['productProfile'] as String,
    general: json['general'] as String,
  );
}

Map<String, dynamic> _$GeneralToJson(General instance) => <String, dynamic>{
      'general': instance.general,
      'productProfile': instance.productProfile,
    };
