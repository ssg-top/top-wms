package com.top.effitopia.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageRequestDTO<T> {

    private T searchCond;
    private String keyword;

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    private int size = 10;

    private String link;

    public int getSkip(){
        return (page - 1) * 10;
    }

    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);
        }
        return link;
    }
}
