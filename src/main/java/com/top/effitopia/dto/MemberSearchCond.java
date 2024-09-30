package com.top.effitopia.dto;

import com.top.effitopia.enumeration.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSearchCond {

    private String username;
    private MemberRole role;

}
