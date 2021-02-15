class SSLCTransactionInfoModel {
  String aPIConnect;
  String amount;
  String bankTranId;
  String baseFair;
  String cardBrand;
  String cardIssuer;
  String cardIssuerCountry;
  String cardIssuerCountryCode;
  String cardNo;
  String cardType;
  String currencyAmount;
  String currencyRate;
  String currencyType;
  String gwVersion;
  String riskLevel;
  String riskTitle;
  String sessionkey;
  String status;
  String storeAmount;
  String tranDate;
  String tranId;
  String valId;
  String validatedOn;
  String valueA;
  String valueB;
  String valueC;
  String valueD;

  SSLCTransactionInfoModel(
      {this.aPIConnect,
      this.amount,
      this.bankTranId,
      this.baseFair,
      this.cardBrand,
      this.cardIssuer,
      this.cardIssuerCountry,
      this.cardIssuerCountryCode,
      this.cardNo,
      this.cardType,
      this.currencyAmount,
      this.currencyRate,
      this.currencyType,
      this.gwVersion,
      this.riskLevel,
      this.riskTitle,
      this.sessionkey,
      this.status,
      this.storeAmount,
      this.tranDate,
      this.tranId,
      this.valId,
      this.validatedOn,
      this.valueA,
      this.valueB,
      this.valueC,
      this.valueD});

  SSLCTransactionInfoModel.fromJson(Map<String, dynamic> json) {
    aPIConnect = json['APIConnect'];
    amount = json['amount'];
    bankTranId = json['bank_tran_id'];
    baseFair = json['base_fair'];
    cardBrand = json['card_brand'];
    cardIssuer = json['card_issuer'];
    cardIssuerCountry = json['card_issuer_country'];
    cardIssuerCountryCode = json['card_issuer_country_code'];
    cardNo = json['card_no'];
    cardType = json['card_type'];
    currencyAmount = json['currency_amount'];
    currencyRate = json['currency_rate'];
    currencyType = json['currency_type'];
    gwVersion = json['gw_version'];
    riskLevel = json['risk_level'];
    riskTitle = json['risk_title'];
    sessionkey = json['sessionkey'];
    status = json['status'];
    storeAmount = json['store_amount'];
    tranDate = json['tran_date'];
    tranId = json['tran_id'];
    valId = json['val_id'];
    validatedOn = json['validated_on'];
    valueA = json['value_a'];
    valueB = json['value_b'];
    valueC = json['value_c'];
    valueD = json['value_d'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['APIConnect'] = this.aPIConnect;
    data['amount'] = this.amount;
    data['bank_tran_id'] = this.bankTranId;
    data['base_fair'] = this.baseFair;
    data['card_brand'] = this.cardBrand;
    data['card_issuer'] = this.cardIssuer;
    data['card_issuer_country'] = this.cardIssuerCountry;
    data['card_issuer_country_code'] = this.cardIssuerCountryCode;
    data['card_no'] = this.cardNo;
    data['card_type'] = this.cardType;
    data['currency_amount'] = this.currencyAmount;
    data['currency_rate'] = this.currencyRate;
    data['currency_type'] = this.currencyType;
    data['gw_version'] = this.gwVersion;
    data['risk_level'] = this.riskLevel;
    data['risk_title'] = this.riskTitle;
    data['sessionkey'] = this.sessionkey;
    data['status'] = this.status;
    data['store_amount'] = this.storeAmount;
    data['tran_date'] = this.tranDate;
    data['tran_id'] = this.tranId;
    data['val_id'] = this.valId;
    data['validated_on'] = this.validatedOn;
    data['value_a'] = this.valueA;
    data['value_b'] = this.valueB;
    data['value_c'] = this.valueC;
    data['value_d'] = this.valueD;
    return data;
  }
}
