package Biletci.enums;

import lombok.Data;
import lombok.Getter;

public enum ResultMapping {

    SUCCESS(200, "success"),
    CREATED(201, "created"),
    ACCESS_DENIED_EXCEPTION(403, "access_denied_exception"),
    NOT_FOUND(404, "not_found"),
    MAINTENANCE_MODE(503, "maintenance_mode"),
    ERROR_FIELD(410, "error_field"),
    MISSING_FIELD_EXCEPTION(430, "missing_field_exception"),
    OBJECT_NOT_FOUND_EXCEPTION(450, "object_not_found_exception"),
    INVALID_START_DATE_EXCEPTION(450, "invalid_start_date_exception"),
    PASSWORD_NOT_MATCHING(460, "password_not_matching"),
    NO_SOURCE(470, "no_source"),
    DATASOURCE_NOT_AVAILABLE_EXCEPTION(480, "datasource_not_available_exception"),
    UNSUITABLE_FILE_FORMAT_EXCEPTION(490, "unsuitable_file_format_exception"),
    DATABASE_INTEGRITY_PROBLEM(495, "database integrity problem is occured : "),
    UNEXPECTED_ERROR(420, "unexpected_error"),
    PORTAL_ERROR(420, "portal_error"),
    PASSWORD_FORMAT_ERROR(421, "password_must_be_strong"),
    WRONG_OLD_PASSWORD_ERROR(421, "old_password_is_wrong"),
    UNPROCESSABLE_EXCEPTION(422, "something_went_wrong"),
    MULTIPLE_PARENT_EXCEPTION(485, "Object could not have multiple parents."),
    ERROR_NOT_BLANK(409, "Data can not be blank"),
    HASHES_NOT_MATCHING(420, "Password hashes not matching");

    private final int resultCode;
    private final String resultMessage;

    ResultMapping(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public int getResultCode(){
        return resultCode;
    }

    public String getResultMessage(){
        return resultMessage;
    }

}