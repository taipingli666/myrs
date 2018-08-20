package com.choice.domain.entity.external;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 检查/化验 单信息
 * @author Administrator
 *
 */
public class InspectInfo {

    /**
     * 唯一ID
     */
    private String id;
    /**
     * 预约时间
     */
    private String appointmentTime;
    /**
     * 卡号
     */
    private String cardId;
    /**
     * 类型，检查还是化验
     */
    private String type;
    /**
     * 医院编码
     */
    private String hosCode;
    /**
     * 大类编码
     */
    private String classCode;
    /**
     * 大类名称
     */
    private String className;
    /**
     * 样本名称
     */
    private String sampleName;
    /**
     * 样本编码
     */
    private String sampleCode;
	/**
     * 小类编码
     */
    private String miniClassCode;
    /**
     * 小类名称
     */
    private String miniClassName;
    /**
     * 费用
     */
    private Double cost;
    /**
     * 初步诊断编码
     */
    private String icd10;
    /**
     * 诊断
     */
    private String diagnose;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 添加人员
     */
    private String addOper;
    /**
     * 订单流水号
     */
    private String orderId;
    /**
     * 订单状态
     */
    private String payOrderStatus;
    /**
     * 支付终端IP
     */
    private String payerDeviceIp;
    /**
     * 微脉支付订单号
     */
    private String payOrderNo;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 完成状态0：未向医院发送，2已发送成功
     */
    private String completeState;
    /**
     * 门诊登记号
     */
    private String patientRecord;
    /**
     * 处方序号
     */
    private String prescriptionNumber;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 病人名
     */
    private String name;
    /**
     * 性别0:女 1:男
     */
    private Integer sex;
    /**
     * 年龄
     */
    private int age;
    /**
     * 手机号
     */
    private String phoneNumber;
    
    public String getIcd10() {
		return icd10;
	}

	public void setIcd10(String icd10) {
		this.icd10 = icd10;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return sex;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getHosCode() {
		return hosCode;
	}

	public void setHosCode(String hosCode) {
		this.hosCode = hosCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMiniClassCode() {
		return miniClassCode;
	}

	public void setMiniClassCode(String miniClassCode) {
		this.miniClassCode = miniClassCode;
	}

	public String getMiniClassName() {
		return miniClassName;
	}

	public void setMiniClassName(String miniClassName) {
		this.miniClassName = miniClassName;
	}

	public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    } 

    public String getAddOper() {
        return addOper;
    }

    public void setAddOper(String addOper) {
        this.addOper = addOper;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayOrderStatus() {
        return payOrderStatus;
    }

    public void setPayOrderStatus(String payOrderStatus) {
        this.payOrderStatus = payOrderStatus;
    }

    public String getPayerDeviceIp() {
        return payerDeviceIp;
    }

    public void setPayerDeviceIp(String payerDeviceIp) {
        this.payerDeviceIp = payerDeviceIp;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getCompleteState() {
        return completeState;
    }

    public void setCompleteState(String completeState) {
        this.completeState = completeState;
    }

    public String getPatientRecord() {
        return patientRecord;
    }

    public void setPatientRecord(String patientRecord) {
        this.patientRecord = patientRecord;
    }

    public String getPrescriptionNumber() {
        return prescriptionNumber;
    }


	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getSampleCode() {
		return sampleCode;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
    public void setPrescriptionNumber(String prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }
}
