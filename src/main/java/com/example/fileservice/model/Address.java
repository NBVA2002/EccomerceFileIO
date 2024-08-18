package com.example.fileservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    private Province province;
    private District district;
    private Ward ward;
    private String addressDetail;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Province {
        private Long provinceID;
        private String provinceName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class District {
        private Long provinceID;
        private Long districtID;
        private String districtName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Ward {
        private Long districtID;
        private String wardCode;
        private String wardName;
    }

}

