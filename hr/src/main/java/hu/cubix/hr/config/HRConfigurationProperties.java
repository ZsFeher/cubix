package hu.cubix.hr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.auth0.jwt.algorithms.Algorithm;

@ConfigurationProperties(prefix = "hr")
@Component
public class HRConfigurationProperties {

	private WorkingYears workingyears;

	public WorkingYears getWorkingYears()
	{
		return workingyears;
	}

	public void setWorkingYears(WorkingYears workingyears)
	{
		 this.workingyears = workingyears;
	}

	public static class WorkingYears{
		private double firstlevel;
		private double secondlevel;
		private double thirdlevel;

		public double getFirstLevel()
		{
			return firstlevel;
		}
		public double getSecondLevel()
		{
			return secondlevel;
		}
		public double getThirdLevel()
		{
			return thirdlevel;
		}
		public void setFirstLevel(double firstlevel)
		{
			this.firstlevel = firstlevel;
		}
		public void  setSecondLevel(double secondlevel)
		{
			this.secondlevel = secondlevel;
		}
		public void setThirdLevel(double thirdlevel)
		{
			this.thirdlevel= thirdlevel;
		}
	}

	private PayRaise payraise;

	public PayRaise getPayRaise()
	{

		return payraise;
	}

	public void setPayRaise(PayRaise payraise)
	{

		this.payraise = payraise;
	}

	public static class PayRaise
	{
		private int groundlevel;
		private int firstlevel;
		private int secondlevel;
		private int thirdlevel;

		public int getGroundlevel() {

			return groundlevel;
		}

		public void setGroundlevel(int groundlevel) {

			this.groundlevel = groundlevel;
		}

		public int getFirstlevel() {

			return firstlevel;
		}

		public void setFirstlevel(int firstlevel) {

			this.firstlevel = firstlevel;
		}

		public int getSecondlevel() {

			return secondlevel;
		}

		public void setSecondlevel(int secondlevel) {

			this.secondlevel = secondlevel;
		}

		public int getThirdlevel() {
			return thirdlevel;
		}

		public void setThirdlevel(int thirdlevel) {
			this.thirdlevel = thirdlevel;
		}
	}

	private SecurityConf securityConf;

	public SecurityConf getSecurityConf() {
		return securityConf;
	}

	public void setSecurityConf(SecurityConf securityConf) {
		this.securityConf = securityConf;
	}

	public static class SecurityConf
	{
		private String secret;
		private int expiryinterval;
		private String issuer;
		private String algorithm;

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}

		public int getExpiryinterval() {
			return expiryinterval;
		}

		public void setExpiryinterval(int expiryinterval) {
			this.expiryinterval = expiryinterval;
		}

		public String getIssuer() {
			return issuer;
		}

		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}

		public String getAlgorithm() {
			return algorithm;
		}

		public void setAlgorithm(String algorithm) {
			this.algorithm = algorithm;
		}
	}
}
