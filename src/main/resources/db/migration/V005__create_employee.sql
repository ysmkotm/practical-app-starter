CREATE TABLE employee (
	employee_id BIGSERIAL PRIMARY KEY,
	employee_code VARCHAR(20) NOT NULL UNIQUE,
	employee_name VARCHAR(100) NOT NULL,
	employee_name_kana VARCHAR(100),
	email VARCHAR(255) NOT NULL UNIQUE,
	phone_number VARCHAR(20),
	department_id BIGINT NOT NULL,
	position VARCHAR(100),
	hire_date DATE NOT NULL,
	status_code VARCHAR(20) NOT NULL,
	remote_work_flg BOOLEAN NOT NULL DEFAULT FALSE,
	remarks TEXT,
	deleted_flg BOOLEAN NOT NULL DEFAULT FALSE,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_employee_department
		FOREIGN KEY (department_id)
		REFERENCES department (department_id)
);
