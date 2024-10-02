package com.top.effitopia.dto;

import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSearchCond {

    private String username;
    private MemberRole role;
    private MemberStatus status;

}
