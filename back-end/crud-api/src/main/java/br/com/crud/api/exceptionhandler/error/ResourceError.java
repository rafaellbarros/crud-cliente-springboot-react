package br.com.crud.api.exceptionhandler.error;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceError {
    private String tilte;
    private int status;
    private Long timestamp;
    private String userMessage;
    private String developerMessage;
}
