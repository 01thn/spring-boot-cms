package com.thn.springbootcms.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderUtil extends BCryptPasswordEncoder {
}
