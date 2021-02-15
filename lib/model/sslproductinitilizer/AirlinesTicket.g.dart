// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'AirlinesTicket.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

AirlinesTicket _$AirlinesTicketFromJson(Map<String, dynamic> json) {
  return AirlinesTicket(
    productProfile: json['productProfile'] as String,
    hoursTillDeparture: json['hoursTillDeparture'] as String,
    flightType: json['flightType'] as String,
    pnr: json['pnr'] as String,
    journeyFromTo: json['journeyFromTo'] as String,
    thirdPartyBooking: json['thirdPartyBooking'] as String,
  );
}

Map<String, dynamic> _$AirlinesTicketToJson(AirlinesTicket instance) =>
    <String, dynamic>{
      'productProfile': instance.productProfile,
      'hoursTillDeparture': instance.hoursTillDeparture,
      'flightType': instance.flightType,
      'pnr': instance.pnr,
      'journeyFromTo': instance.journeyFromTo,
      'thirdPartyBooking': instance.thirdPartyBooking,
    };
