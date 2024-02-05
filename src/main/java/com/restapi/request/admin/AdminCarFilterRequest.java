package com.restapi.request.admin;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AdminCarFilterRequest {
    List<Integer> seat;
    List<String> manufacturer;
}
