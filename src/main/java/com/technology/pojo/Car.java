package com.technology.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "car")
public class Car {
    @Id
    private Integer id;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "car_licence")
    private String carLicence;

    @Column(name = "IMEI")
    private String imei;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "belone_unit")
    private String beloneUnit;

    @Column(name = "contract_name")
    private String contractName;

    @Column(name = "contract_number")
    private String contractNumber;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "max_passenger")
    private Integer maxPassenger;

    /**
     * 所属车队code
     */
    @Column(name = "car_construction")
    private String carConstruction;

    /**
     * 所属车队名称
     */
    private String construction;

    @Column(name = "contract_addr")
    private String contractAddr;

    /**
     * 车辆注册地
     */
    @Column(name = "reg_addr")
    private String regAddr;

    /**
     * 所属省份
     */
    private String province;

    /**
     * 应用来源的interface_call，接口app参数
     */
    private String app;

    /**
     * 运营中心编号
     */
    @Column(name = "oc_center")
    private String ocCenter;

    /**
     * 车型ID
     */
    @Column(name = "car_class_id")
    private String carClassId;

    /**
     * 服务类型:1 :网络预约出租汽车 2:巡游出租汽车 3 :私人小客车合乘
     */
    @Column(name = "Commercial_Type")
    private String commercialType;

    /**
     * 车牌颜色
     */
    @Column(name = "PlateColor")
    private String platecolor;

    /**
     * 车辆厂牌
     */
    @Column(name = "Brand")
    private String brand;

    /**
     * 车辆型号
     */
    @Column(name = "Model")
    private String model;

    /**
     * 车身颜色
     */
    @Column(name = "VehicleColor")
    private String vehiclecolor;

    /**
     * 发动机号
     */
    @Column(name = "EngineId")
    private String engineid;

    /**
     * 车辆 VIN码
     */
    @Column(name = "Vin")
    private String vin;

    /**
     * 车辆燃料类型
     */
    @Column(name = "FuelType")
    private String fueltype;

    /**
     * 发动机排量
     */
    @Column(name = "EngineDisplace")
    private String enginedisplace;

    /**
     * 车辆照片文件编号
     */
    @Column(name = "PhotoId")
    private String photoid;

    /**
     * 运输证字号
     */
    @Column(name = "Certificate")
    private String certificate;

    /**
     * 车辆运输证发证机构
     */
    @Column(name = "TransAgency")
    private String transagency;

    /**
     * 车辆经营区域
     */
    @Column(name = "TransArea")
    private String transarea;

    /**
     * 车辆通输证有效期起
     */
    @Column(name = "TransDateStart")
    private String transdatestart;

    /**
     * 车辆通输证有效期止
     */
    @Column(name = "TransDateStop")
    private String transdatestop;

    /**
     * 车辆检修状态 0:未检修 1 :已检修 2 :未知
     */
    @Column(name = "FixState")
    private String fixstate;

    /**
     * 车辆下次年检时间
     */
    @Column(name = "NextFixDate")
    private String nextfixdate;

    /**
     * 车辆年度审验状态
     */
    @Column(name = "CheckState")
    private String checkstate;

    /**
     * 发票打印设备序列号
     */
    @Column(name = "FeePrintId")
    private String feeprintid;

    /**
     * 卫星定位装置品牌
     */
    @Column(name = "GPSBrand")
    private String gpsbrand;

    /**
     * 卫星定位装置型号
     */
    @Column(name = "GPSModel")
    private String gpsmodel;

    /**
     * 卫星定位装置 IMEI号
     */
    @Column(name = "GPSIMEI")
    private String gpsimei;

    /**
     * 卫星定位设备安装日期
     */
    @Column(name = "GPSInstallDate")
    private String gpsinstalldate;

    /**
     * 报备日期
     */
    @Column(name = "RegisterDate")
    private String registerdate;

    /**
     * 运价类型编码
     */
    @Column(name = "FareType")
    private String faretype;

    /**
     * 保险公司名称
     */
    @Column(name = "InsurCom")
    private String insurcom;

    /**
     * 保险号
     */
    @Column(name = "InsurNum")
    private String insurnum;

    /**
     * 保险类型
     */
    @Column(name = "InsurType")
    private String insurtype;

    /**
     * 保险金额 单位/元
     */
    @Column(name = "InsurCount")
    private String insurcount;

    /**
     * 保险生效时间
     */
    @Column(name = "InsurEff")
    private String insureff;

    /**
     * 保险到期时间
     */
    @Column(name = "InsurExp")
    private String insurexp;

    /**
     * 车辆注册时间
     */
    @Column(name = "CertifyDateA")
    private String certifydatea;

    /**
     * 车辆初次登记时间
     */
    @Column(name = "CertifyDateB")
    private String certifydateb;

    /**
     * 车辆来源:0未知,1司机端注册,2后台新增
     */
    @Column(name = "car_origin")
    private Byte carOrigin;

    /**
     * 使用性质
     */
    @Column(name = "use_property")
    private String useProperty;

    /**
     * 行驶证上车辆类型
     */
    @Column(name = "driver_permit_type")
    private String driverPermitType;

    /**
     * 行驶证上所有人名称
     */
    @Column(name = "driver_permit_owner")
    private String driverPermitOwner;

    /**
     * 车辆管理-车辆类型
     */
    @Column(name = "vehicle_type")
    private String vehicleType;

    /**
     * 车辆行驶证号
     */
    @Column(name = "vehicle_driving_license")
    private String vehicleDrivingLicense;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return car_number
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * @param carNumber
     */
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    /**
     * @return car_licence
     */
    public String getCarLicence() {
        return carLicence;
    }

    /**
     * @param carLicence
     */
    public void setCarLicence(String carLicence) {
        this.carLicence = carLicence == null ? null : carLicence.trim();
    }

    /**
     * @return IMEI
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     * @return car_type
     */
    public String getCarType() {
        return carType;
    }

    /**
     * @param carType
     */
    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    /**
     * @return belone_unit
     */
    public String getBeloneUnit() {
        return beloneUnit;
    }

    /**
     * @param beloneUnit
     */
    public void setBeloneUnit(String beloneUnit) {
        this.beloneUnit = beloneUnit == null ? null : beloneUnit.trim();
    }

    /**
     * @return contract_name
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * @param contractName
     */
    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    /**
     * @return contract_number
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * @param contractNumber
     */
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber == null ? null : contractNumber.trim();
    }

    /**
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return max_passenger
     */
    public Integer getMaxPassenger() {
        return maxPassenger;
    }

    /**
     * @param maxPassenger
     */
    public void setMaxPassenger(Integer maxPassenger) {
        this.maxPassenger = maxPassenger;
    }

    /**
     * 获取所属车队code
     *
     * @return car_construction - 所属车队code
     */
    public String getCarConstruction() {
        return carConstruction;
    }

    /**
     * 设置所属车队code
     *
     * @param carConstruction 所属车队code
     */
    public void setCarConstruction(String carConstruction) {
        this.carConstruction = carConstruction == null ? null : carConstruction.trim();
    }

    /**
     * 获取所属车队名称
     *
     * @return construction - 所属车队名称
     */
    public String getConstruction() {
        return construction;
    }

    /**
     * 设置所属车队名称
     *
     * @param construction 所属车队名称
     */
    public void setConstruction(String construction) {
        this.construction = construction == null ? null : construction.trim();
    }

    /**
     * @return contract_addr
     */
    public String getContractAddr() {
        return contractAddr;
    }

    /**
     * @param contractAddr
     */
    public void setContractAddr(String contractAddr) {
        this.contractAddr = contractAddr == null ? null : contractAddr.trim();
    }

    /**
     * 获取车辆注册地
     *
     * @return reg_addr - 车辆注册地
     */
    public String getRegAddr() {
        return regAddr;
    }

    /**
     * 设置车辆注册地
     *
     * @param regAddr 车辆注册地
     */
    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr == null ? null : regAddr.trim();
    }

    /**
     * 获取所属省份
     *
     * @return province - 所属省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置所属省份
     *
     * @param province 所属省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取应用来源的interface_call，接口app参数
     *
     * @return app - 应用来源的interface_call，接口app参数
     */
    public String getApp() {
        return app;
    }

    /**
     * 设置应用来源的interface_call，接口app参数
     *
     * @param app 应用来源的interface_call，接口app参数
     */
    public void setApp(String app) {
        this.app = app == null ? null : app.trim();
    }

    /**
     * 获取运营中心编号
     *
     * @return oc_center - 运营中心编号
     */
    public String getOcCenter() {
        return ocCenter;
    }

    /**
     * 设置运营中心编号
     *
     * @param ocCenter 运营中心编号
     */
    public void setOcCenter(String ocCenter) {
        this.ocCenter = ocCenter == null ? null : ocCenter.trim();
    }

    /**
     * 获取车型ID
     *
     * @return car_class_id - 车型ID
     */
    public String getCarClassId() {
        return carClassId;
    }

    /**
     * 设置车型ID
     *
     * @param carClassId 车型ID
     */
    public void setCarClassId(String carClassId) {
        this.carClassId = carClassId == null ? null : carClassId.trim();
    }

    /**
     * 获取服务类型:1 :网络预约出租汽车 2:巡游出租汽车 3 :私人小客车合乘
     *
     * @return Commercial_Type - 服务类型:1 :网络预约出租汽车 2:巡游出租汽车 3 :私人小客车合乘
     */
    public String getCommercialType() {
        return commercialType;
    }

    /**
     * 设置服务类型:1 :网络预约出租汽车 2:巡游出租汽车 3 :私人小客车合乘
     *
     * @param commercialType 服务类型:1 :网络预约出租汽车 2:巡游出租汽车 3 :私人小客车合乘
     */
    public void setCommercialType(String commercialType) {
        this.commercialType = commercialType == null ? null : commercialType.trim();
    }

    /**
     * 获取车牌颜色
     *
     * @return PlateColor - 车牌颜色
     */
    public String getPlatecolor() {
        return platecolor;
    }

    /**
     * 设置车牌颜色
     *
     * @param platecolor 车牌颜色
     */
    public void setPlatecolor(String platecolor) {
        this.platecolor = platecolor == null ? null : platecolor.trim();
    }

    /**
     * 获取车辆厂牌
     *
     * @return Brand - 车辆厂牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置车辆厂牌
     *
     * @param brand 车辆厂牌
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    /**
     * 获取车辆型号
     *
     * @return Model - 车辆型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置车辆型号
     *
     * @param model 车辆型号
     */
    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    /**
     * 获取车身颜色
     *
     * @return VehicleColor - 车身颜色
     */
    public String getVehiclecolor() {
        return vehiclecolor;
    }

    /**
     * 设置车身颜色
     *
     * @param vehiclecolor 车身颜色
     */
    public void setVehiclecolor(String vehiclecolor) {
        this.vehiclecolor = vehiclecolor == null ? null : vehiclecolor.trim();
    }

    /**
     * 获取发动机号
     *
     * @return EngineId - 发动机号
     */
    public String getEngineid() {
        return engineid;
    }

    /**
     * 设置发动机号
     *
     * @param engineid 发动机号
     */
    public void setEngineid(String engineid) {
        this.engineid = engineid == null ? null : engineid.trim();
    }

    /**
     * 获取车辆 VIN码
     *
     * @return Vin - 车辆 VIN码
     */
    public String getVin() {
        return vin;
    }

    /**
     * 设置车辆 VIN码
     *
     * @param vin 车辆 VIN码
     */
    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    /**
     * 获取车辆燃料类型
     *
     * @return FuelType - 车辆燃料类型
     */
    public String getFueltype() {
        return fueltype;
    }

    /**
     * 设置车辆燃料类型
     *
     * @param fueltype 车辆燃料类型
     */
    public void setFueltype(String fueltype) {
        this.fueltype = fueltype == null ? null : fueltype.trim();
    }

    /**
     * 获取发动机排量
     *
     * @return EngineDisplace - 发动机排量
     */
    public String getEnginedisplace() {
        return enginedisplace;
    }

    /**
     * 设置发动机排量
     *
     * @param enginedisplace 发动机排量
     */
    public void setEnginedisplace(String enginedisplace) {
        this.enginedisplace = enginedisplace == null ? null : enginedisplace.trim();
    }

    /**
     * 获取车辆照片文件编号
     *
     * @return PhotoId - 车辆照片文件编号
     */
    public String getPhotoid() {
        return photoid;
    }

    /**
     * 设置车辆照片文件编号
     *
     * @param photoid 车辆照片文件编号
     */
    public void setPhotoid(String photoid) {
        this.photoid = photoid == null ? null : photoid.trim();
    }

    /**
     * 获取运输证字号
     *
     * @return Certificate - 运输证字号
     */
    public String getCertificate() {
        return certificate;
    }

    /**
     * 设置运输证字号
     *
     * @param certificate 运输证字号
     */
    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }

    /**
     * 获取车辆运输证发证机构
     *
     * @return TransAgency - 车辆运输证发证机构
     */
    public String getTransagency() {
        return transagency;
    }

    /**
     * 设置车辆运输证发证机构
     *
     * @param transagency 车辆运输证发证机构
     */
    public void setTransagency(String transagency) {
        this.transagency = transagency == null ? null : transagency.trim();
    }

    /**
     * 获取车辆经营区域
     *
     * @return TransArea - 车辆经营区域
     */
    public String getTransarea() {
        return transarea;
    }

    /**
     * 设置车辆经营区域
     *
     * @param transarea 车辆经营区域
     */
    public void setTransarea(String transarea) {
        this.transarea = transarea == null ? null : transarea.trim();
    }

    /**
     * 获取车辆通输证有效期起
     *
     * @return TransDateStart - 车辆通输证有效期起
     */
    public String getTransdatestart() {
        return transdatestart;
    }

    /**
     * 设置车辆通输证有效期起
     *
     * @param transdatestart 车辆通输证有效期起
     */
    public void setTransdatestart(String transdatestart) {
        this.transdatestart = transdatestart == null ? null : transdatestart.trim();
    }

    /**
     * 获取车辆通输证有效期止
     *
     * @return TransDateStop - 车辆通输证有效期止
     */
    public String getTransdatestop() {
        return transdatestop;
    }

    /**
     * 设置车辆通输证有效期止
     *
     * @param transdatestop 车辆通输证有效期止
     */
    public void setTransdatestop(String transdatestop) {
        this.transdatestop = transdatestop == null ? null : transdatestop.trim();
    }

    /**
     * 获取车辆检修状态 0:未检修 1 :已检修 2 :未知
     *
     * @return FixState - 车辆检修状态 0:未检修 1 :已检修 2 :未知
     */
    public String getFixstate() {
        return fixstate;
    }

    /**
     * 设置车辆检修状态 0:未检修 1 :已检修 2 :未知
     *
     * @param fixstate 车辆检修状态 0:未检修 1 :已检修 2 :未知
     */
    public void setFixstate(String fixstate) {
        this.fixstate = fixstate == null ? null : fixstate.trim();
    }

    /**
     * 获取车辆下次年检时间
     *
     * @return NextFixDate - 车辆下次年检时间
     */
    public String getNextfixdate() {
        return nextfixdate;
    }

    /**
     * 设置车辆下次年检时间
     *
     * @param nextfixdate 车辆下次年检时间
     */
    public void setNextfixdate(String nextfixdate) {
        this.nextfixdate = nextfixdate == null ? null : nextfixdate.trim();
    }

    /**
     * 获取车辆年度审验状态
     *
     * @return CheckState - 车辆年度审验状态
     */
    public String getCheckstate() {
        return checkstate;
    }

    /**
     * 设置车辆年度审验状态
     *
     * @param checkstate 车辆年度审验状态
     */
    public void setCheckstate(String checkstate) {
        this.checkstate = checkstate == null ? null : checkstate.trim();
    }

    /**
     * 获取发票打印设备序列号
     *
     * @return FeePrintId - 发票打印设备序列号
     */
    public String getFeeprintid() {
        return feeprintid;
    }

    /**
     * 设置发票打印设备序列号
     *
     * @param feeprintid 发票打印设备序列号
     */
    public void setFeeprintid(String feeprintid) {
        this.feeprintid = feeprintid == null ? null : feeprintid.trim();
    }

    /**
     * 获取卫星定位装置品牌
     *
     * @return GPSBrand - 卫星定位装置品牌
     */
    public String getGpsbrand() {
        return gpsbrand;
    }

    /**
     * 设置卫星定位装置品牌
     *
     * @param gpsbrand 卫星定位装置品牌
     */
    public void setGpsbrand(String gpsbrand) {
        this.gpsbrand = gpsbrand == null ? null : gpsbrand.trim();
    }

    /**
     * 获取卫星定位装置型号
     *
     * @return GPSModel - 卫星定位装置型号
     */
    public String getGpsmodel() {
        return gpsmodel;
    }

    /**
     * 设置卫星定位装置型号
     *
     * @param gpsmodel 卫星定位装置型号
     */
    public void setGpsmodel(String gpsmodel) {
        this.gpsmodel = gpsmodel == null ? null : gpsmodel.trim();
    }

    /**
     * 获取卫星定位装置 IMEI号
     *
     * @return GPSIMEI - 卫星定位装置 IMEI号
     */
    public String getGpsimei() {
        return gpsimei;
    }

    /**
     * 设置卫星定位装置 IMEI号
     *
     * @param gpsimei 卫星定位装置 IMEI号
     */
    public void setGpsimei(String gpsimei) {
        this.gpsimei = gpsimei == null ? null : gpsimei.trim();
    }

    /**
     * 获取卫星定位设备安装日期
     *
     * @return GPSInstallDate - 卫星定位设备安装日期
     */
    public String getGpsinstalldate() {
        return gpsinstalldate;
    }

    /**
     * 设置卫星定位设备安装日期
     *
     * @param gpsinstalldate 卫星定位设备安装日期
     */
    public void setGpsinstalldate(String gpsinstalldate) {
        this.gpsinstalldate = gpsinstalldate == null ? null : gpsinstalldate.trim();
    }

    /**
     * 获取报备日期
     *
     * @return RegisterDate - 报备日期
     */
    public String getRegisterdate() {
        return registerdate;
    }

    /**
     * 设置报备日期
     *
     * @param registerdate 报备日期
     */
    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate == null ? null : registerdate.trim();
    }

    /**
     * 获取运价类型编码
     *
     * @return FareType - 运价类型编码
     */
    public String getFaretype() {
        return faretype;
    }

    /**
     * 设置运价类型编码
     *
     * @param faretype 运价类型编码
     */
    public void setFaretype(String faretype) {
        this.faretype = faretype == null ? null : faretype.trim();
    }

    /**
     * 获取保险公司名称
     *
     * @return InsurCom - 保险公司名称
     */
    public String getInsurcom() {
        return insurcom;
    }

    /**
     * 设置保险公司名称
     *
     * @param insurcom 保险公司名称
     */
    public void setInsurcom(String insurcom) {
        this.insurcom = insurcom == null ? null : insurcom.trim();
    }

    /**
     * 获取保险号
     *
     * @return InsurNum - 保险号
     */
    public String getInsurnum() {
        return insurnum;
    }

    /**
     * 设置保险号
     *
     * @param insurnum 保险号
     */
    public void setInsurnum(String insurnum) {
        this.insurnum = insurnum == null ? null : insurnum.trim();
    }

    /**
     * 获取保险类型
     *
     * @return InsurType - 保险类型
     */
    public String getInsurtype() {
        return insurtype;
    }

    /**
     * 设置保险类型
     *
     * @param insurtype 保险类型
     */
    public void setInsurtype(String insurtype) {
        this.insurtype = insurtype == null ? null : insurtype.trim();
    }

    /**
     * 获取保险金额 单位/元
     *
     * @return InsurCount - 保险金额 单位/元
     */
    public String getInsurcount() {
        return insurcount;
    }

    /**
     * 设置保险金额 单位/元
     *
     * @param insurcount 保险金额 单位/元
     */
    public void setInsurcount(String insurcount) {
        this.insurcount = insurcount == null ? null : insurcount.trim();
    }

    /**
     * 获取保险生效时间
     *
     * @return InsurEff - 保险生效时间
     */
    public String getInsureff() {
        return insureff;
    }

    /**
     * 设置保险生效时间
     *
     * @param insureff 保险生效时间
     */
    public void setInsureff(String insureff) {
        this.insureff = insureff == null ? null : insureff.trim();
    }

    /**
     * 获取保险到期时间
     *
     * @return InsurExp - 保险到期时间
     */
    public String getInsurexp() {
        return insurexp;
    }

    /**
     * 设置保险到期时间
     *
     * @param insurexp 保险到期时间
     */
    public void setInsurexp(String insurexp) {
        this.insurexp = insurexp == null ? null : insurexp.trim();
    }

    /**
     * 获取车辆注册时间
     *
     * @return CertifyDateA - 车辆注册时间
     */
    public String getCertifydatea() {
        return certifydatea;
    }

    /**
     * 设置车辆注册时间
     *
     * @param certifydatea 车辆注册时间
     */
    public void setCertifydatea(String certifydatea) {
        this.certifydatea = certifydatea == null ? null : certifydatea.trim();
    }

    /**
     * 获取车辆初次登记时间
     *
     * @return CertifyDateB - 车辆初次登记时间
     */
    public String getCertifydateb() {
        return certifydateb;
    }

    /**
     * 设置车辆初次登记时间
     *
     * @param certifydateb 车辆初次登记时间
     */
    public void setCertifydateb(String certifydateb) {
        this.certifydateb = certifydateb == null ? null : certifydateb.trim();
    }

    /**
     * 获取车辆来源:0未知,1司机端注册,2后台新增
     *
     * @return car_origin - 车辆来源:0未知,1司机端注册,2后台新增
     */
    public Byte getCarOrigin() {
        return carOrigin;
    }

    /**
     * 设置车辆来源:0未知,1司机端注册,2后台新增
     *
     * @param carOrigin 车辆来源:0未知,1司机端注册,2后台新增
     */
    public void setCarOrigin(Byte carOrigin) {
        this.carOrigin = carOrigin;
    }

    /**
     * 获取使用性质
     *
     * @return use_property - 使用性质
     */
    public String getUseProperty() {
        return useProperty;
    }

    /**
     * 设置使用性质
     *
     * @param useProperty 使用性质
     */
    public void setUseProperty(String useProperty) {
        this.useProperty = useProperty == null ? null : useProperty.trim();
    }

    /**
     * 获取行驶证上车辆类型
     *
     * @return driver_permit_type - 行驶证上车辆类型
     */
    public String getDriverPermitType() {
        return driverPermitType;
    }

    /**
     * 设置行驶证上车辆类型
     *
     * @param driverPermitType 行驶证上车辆类型
     */
    public void setDriverPermitType(String driverPermitType) {
        this.driverPermitType = driverPermitType == null ? null : driverPermitType.trim();
    }

    /**
     * 获取行驶证上所有人名称
     *
     * @return driver_permit_owner - 行驶证上所有人名称
     */
    public String getDriverPermitOwner() {
        return driverPermitOwner;
    }

    /**
     * 设置行驶证上所有人名称
     *
     * @param driverPermitOwner 行驶证上所有人名称
     */
    public void setDriverPermitOwner(String driverPermitOwner) {
        this.driverPermitOwner = driverPermitOwner == null ? null : driverPermitOwner.trim();
    }

    /**
     * 获取车辆管理-车辆类型
     *
     * @return vehicle_type - 车辆管理-车辆类型
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * 设置车辆管理-车辆类型
     *
     * @param vehicleType 车辆管理-车辆类型
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType == null ? null : vehicleType.trim();
    }

    /**
     * 获取车辆行驶证号
     *
     * @return vehicle_driving_license - 车辆行驶证号
     */
    public String getVehicleDrivingLicense() {
        return vehicleDrivingLicense;
    }

    /**
     * 设置车辆行驶证号
     *
     * @param vehicleDrivingLicense 车辆行驶证号
     */
    public void setVehicleDrivingLicense(String vehicleDrivingLicense) {
        this.vehicleDrivingLicense = vehicleDrivingLicense == null ? null : vehicleDrivingLicense.trim();
    }
}