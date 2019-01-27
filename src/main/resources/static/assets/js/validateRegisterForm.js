var MIN_PASSWORD_LENGTH = 8;

function validateRegisterForm(form) {
	return (
		validatePassword(form.password) ||
		validateConfPassword(form.password, form.confPassword)
	);
}

function validatePassword(password) {
	if (password.length < MIN_PASSWORD_LENGTH) {
		return (
			'Password must be longer than ' + MIN_PASSWORD_LENGTH + ' characters'
		);
	}

	if (!/[A-Z]/i.test(password)) {
		return 'Password must contain at least one alphabetic character';
	}

	if (!/[0-9]/.test(password)) {
		return 'Password must contain at least one number';
	}

	if (!/[^A-Za-z0-9]/.test(password)) {
		return 'Password must contain at least one special character';
	}

	return '';
}

function validateConfPassword(password, confPassword) {
	return password !== confPassword ? 'Passwords do not match' : '';
}
