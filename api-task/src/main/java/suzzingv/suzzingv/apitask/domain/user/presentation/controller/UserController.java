package suzzingv.suzzingv.apitask.domain.user.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import suzzingv.suzzingv.apitask.domain.user.application.UserService;
import suzzingv.suzzingv.apitask.domain.user.presentation.dto.res.PostSumResponse;
import suzzingv.suzzingv.apitask.domain.user.presentation.exception.UserException;
import suzzingv.suzzingv.apitask.global.response.properties.ErrorCode;
import suzzingv.suzzingv.apitask.domain.user.presentation.dto.res.SuccessResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<SuccessResponse> success() {
        SuccessResponse response = SuccessResponse.builder()
                .message("server check")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sum")
    public ResponseEntity<PostSumResponse> getPostSum() {
        PostSumResponse response = userService.getPostSum();
        return ResponseEntity.ok(response);
    }
}
