package gr.dataverse.demoRegLog.pojos;

public class RegLogResponse {
	
	 	private String status;
	    private String message;
	    private String userId;
	    
	    public RegLogResponse(String status, String message) {
			super();
			this.status = status;
			this.message = message;
			
		}
		
	    public RegLogResponse(String status, String message, String userId) {
			super();
			this.status = status;
			this.message = message;
			this.userId = userId;
		}
	    
	    
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}

		@Override
		public String toString() {
			return "RegLogResponse [status=" + status + ", message=" + message + ", userId=" + userId + "]";
		}


}
