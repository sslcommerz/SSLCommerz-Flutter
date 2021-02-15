import 'package:flutter/cupertino.dart';
import 'package:json_annotation/json_annotation.dart';

part 'General.g.dart';

@JsonSerializable()
class General {
  String general;
  String productProfile;

  General({@required this.productProfile, @required this.general});
  Map<String, dynamic> toJson() => _$GeneralToJson(this);
  factory General.fromJson(Map<String, dynamic> json) =>
      _$GeneralFromJson(json);

  String getProductProfile() {
    return this.productProfile;
  }

  void setProductProfile(String productProfile) {
    this.productProfile = productProfile;
  }

  String getGeneral() {
    return this.general;
  }

  void setGeneral(String general) {
    this.general = general;
  }
}
