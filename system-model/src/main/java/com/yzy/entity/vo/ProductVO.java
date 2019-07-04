package com.yzy.entity.vo;


public class ProductVO {
	    private Long id;
	    private Long factoryId;
	    private String factoryName;
	    /**
	     * 产品名称
	     */
	    private String productName;

	    /**
	     * 治疗类型id
	     */
	    private Long baseCureId;
	    /**
	     * 治疗类型名称
	     */
	    private String cureName;

	    /**
	     * 材质
	     */
	    private String textureName;

	    /**
	     * 品牌
	     */
	    private String brandName;

	    private String price;
	    
	    /**
	     * 状态0:删除/1:正常
	     */
	    private Integer status;

	    /**
	     * @return id
	     */
	    public Long getId() {
	        return id;
	    }

	    /**
	     * @param id
	     */
	    public void setId(Long id) {
	        this.id = id;
	    }

	    /**
	     * 获取产品名称
	     *
	     * @return product_name - 产品名称
	     */
	    public String getProductName() {
	        return productName;
	    }

	    /**
	     * 设置产品名称
	     *
	     * @param productName 产品名称
	     */
	    public void setProductName(String productName) {
	        this.productName = productName;
	    }

	    /**
	     * 获取治疗类型id
	     *
	     * @return base_cure_id - 治疗类型id
	     */
	    public Long getBaseCureId() {
	        return baseCureId;
	    }

	    /**
	     * 设置治疗类型id
	     *
	     * @param baseCureId 治疗类型id
	     */
	    public void setBaseCureId(Long baseCureId) {
	        this.baseCureId = baseCureId;
	    }

	    /**
	     * 获取材质
	     *
	     * @return texture_name - 材质
	     */
	    public String getTextureName() {
	        return textureName;
	    }

	    /**
	     * 设置材质
	     *
	     * @param textureName 材质
	     */
	    public void setTextureName(String textureName) {
	        this.textureName = textureName;
	    }

	    /**
	     * 获取品牌
	     *
	     * @return brand_name - 品牌
	     */
	    public String getBrandName() {
	        return brandName;
	    }

	    /**
	     * 设置品牌
	     *
	     * @param brandName 品牌
	     */
	    public void setBrandName(String brandName) {
	        this.brandName = brandName;
	    }

	    /**
	     * 获取状态0:删除/1:正常
	     *
	     * @return status - 状态0:删除/1:正常
	     */
	    public Integer getStatus() {
	        return status;
	    }

	    /**
	     * 设置状态0:删除/1:正常
	     *
	     * @param status 状态0:删除/1:正常
	     */
	    public void setStatus(Integer status) {
	        this.status = status;
	    }

	    /**
	     * 获取治疗类型名称
	     *
	     */
		public String getCureName() {
			return cureName;
		}

		/**
	     * 设置治疗类型名称
	     *
	     */
		public void setCureName(String cureName) {
			this.cureName = cureName;
		}

		
		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public Long getFactoryId() {
			return factoryId;
		}

		public void setFactoryId(Long factoryId) {
			this.factoryId = factoryId;
		}

		public String getFactoryName() {
			return factoryName;
		}

		public void setFactoryName(String factoryName) {
			this.factoryName = factoryName;
		}

		@Override
		public String toString() {
			return "ProductVO [id=" + id + ", factoryId=" + factoryId + ", factoryName=" + factoryName
					+ ", productName=" + productName + ", baseCureId=" + baseCureId + ", cureName=" + cureName
					+ ", textureName=" + textureName + ", brandName=" + brandName + ", price=" + price + ", status="
					+ status + "]";
		}
		
}
