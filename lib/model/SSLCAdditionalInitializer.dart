import 'package:json_annotation/json_annotation.dart';
part 'SSLCAdditionalInitializer.g.dart';

@JsonSerializable()
 class SSLCAdditionalInitializer {
   String valueA;
   String valueB;
   String valueC;
   String valueD;

   SSLCAdditionalInitializer({this.valueA, this.valueB, this.valueC, this.valueD});

   Map<String, dynamic> toJson() => _$SSLCAdditionalInitializerToJson(this);
   factory SSLCAdditionalInitializer.fromJson(Map<String, dynamic> json) => _$SSLCAdditionalInitializerFromJson(json);

   String getValueA() {
    return this.valueA;
  }

   void setValueA(String valueA) {
    this.valueA = valueA;
  }

   String getValueB() {
    return this.valueB;
  }

   void setValueB(String valueB) {
    this.valueB = valueB;
  }

   String getValueC() {
    return this.valueC;
  }

   void setValueC(String valueC) {
    this.valueC = valueC;
  }

   String getValueD() {
    return this.valueD;
  }

   void setValueD(String valueD) {
    this.valueD = valueD;
  }
}
