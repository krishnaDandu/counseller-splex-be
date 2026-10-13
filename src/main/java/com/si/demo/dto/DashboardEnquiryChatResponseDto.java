package com.si.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardEnquiryChatResponseDto {

    private Long totalEnquiries;
    private Long newEnquiries;
    private Long enrolledEnquiries;
    private Long lostEnquiries;

    public DashboardEnquiryChatResponseDto(long count, long newEnquiries, long enrolled, long lost) {
        this.totalEnquiries = count;
        this.newEnquiries = newEnquiries;
        this.enrolledEnquiries = enrolled;
        this.lostEnquiries = lost;
    }
}
