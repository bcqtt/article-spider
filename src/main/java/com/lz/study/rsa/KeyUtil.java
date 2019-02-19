package com.lz.study.rsa;
 
public class KeyUtil {
	//服务端的RSA公钥(Base64编码)
	public final static String SERVER_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgZE5dTMMNaA9MVNlcPkNLxezFOoYnQni\r\n" + 
			"s5JIjOHa/ImOyqPVyiI+2/EOLM1cXoS+q0wIghqwIYfiPgMVt22WCTfyS5wRvjuaX5nYlgejGSMW\r\n" + 
			"upXOY2909TKR4ZXXZ1CZIB1sUJxa7jH9JiQL92qlJmzjWurL4vyd0DocNyefPs6lKcytRKTmTtbM\r\n" + 
			"pLd7Ox6DU8yDmLvRRECaEA8mDaGPzHi8RqHu7Y1EXCv2u/Hq8kzRhJF/KG1o+q+FCLTIWzte0ruY\r\n" + 
			"3iabSfNOYQ/DdL2BrxGDXp+gY1ajNyw5dJYw9yLR1aVzU9NxiElxdfz5YoKjZKVcHIMvx5S9CuKP\r\n" + 
			"7hJi0wIDAQAB";
	
	//服务端的RSA私钥(Base64编码)
	public final static String SERVER_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCBkTl1Mww1oD0xU2Vw+Q0vF7MU\r\n" + 
			"6hidCeKzkkiM4dr8iY7Ko9XKIj7b8Q4szVxehL6rTAiCGrAhh+I+AxW3bZYJN/JLnBG+O5pfmdiW\r\n" + 
			"B6MZIxa6lc5jb3T1MpHhlddnUJkgHWxQnFruMf0mJAv3aqUmbONa6svi/J3QOhw3J58+zqUpzK1E\r\n" + 
			"pOZO1sykt3s7HoNTzIOYu9FEQJoQDyYNoY/MeLxGoe7tjURcK/a78eryTNGEkX8obWj6r4UItMhb\r\n" + 
			"O17Su5jeJptJ805hD8N0vYGvEYNen6BjVqM3LDl0ljD3ItHVpXNT03GISXF1/PligqNkpVwcgy/H\r\n" + 
			"lL0K4o/uEmLTAgMBAAECggEAaYhkCHXfx/4QWZDX20C/4E+paauTCLX4lNLdjQKZX4CbcaoohE+I\r\n" + 
			"a5TYOt26ErKEqvlvJKmgNG99c40HrzPvNOsAy1z/hrb9ckjZBLqLufm+lZNXW954oQ7ji56EqH4Z\r\n" + 
			"evL8OlxgJAtLajmKFagjhDzEolUEZInl8KikOrhnGLwGA8NMB/G3V7v9Dd9LSV2QRCTezGI9Shr0\r\n" + 
			"9AGy8MRsuIkkccZKuUq4u0H5pZALTmWcgYKB1QjoEiZ4cBxElB1KS/m79iEfqveCXuePEN8/npzo\r\n" + 
			"HVJtvytXDGekYhgWHDS10cX7R0UuMDfLODluKFSuEY4SJFPl7jCvsdp45loIwQKBgQDIE2nDQ6eW\r\n" + 
			"PUVDb2POqiWTpLNZCaWIhZ0EymoogAWSNSBJiyVM6sJIUGhD2PLHnLa1tJagtqYCnFNGjaWOJe3R\r\n" + 
			"x39xNjtZOUOfBOqIrzZz22Wsu1OM8jeU9ZQ6zBKfkMs1rzWA/rQ0BcyjfTNntvwoE1A4xWoawgEU\r\n" + 
			"Z1+KBS/OuQKBgQClyIPy1O+8Bll6lO8v1Xi6HcLFJoOtkev3Q88y1RHiLHxuYgqk0Eu2oa/+R2mj\r\n" + 
			"mcwnLFBBrnE5PCnlF/51O66WV3gLkoZE3ZEjj26dd5gf7Uc/bNLmaUUpf2Oe7PoBEFl67p12nSwH\r\n" + 
			"So8WwLsc1fbTh9TXl+iEwQXKMnlShgUX6wKBgQCT1Dw2CXv+9yCBNgFxxqfjEjNt20HSwJ6G1qRf\r\n" + 
			"blAvek9z53rU/TQ1yCtxzg98S594XIlbD/lTPnwNSkWD5SR19siENsTdJQGAslo0Sfma2wWh/fs6\r\n" + 
			"zhEhX955HyW3fn2XGfEXcZpmQc61EaH0xPBFEBgbCkpeoO+5SrP9v91WCQKBgFJd4njWpNmFShWm\r\n" + 
			"Q4Q/ioDl4mwza37kHhm23g4CYKU/tv7cxS8HsmZlxNwQPQMkmZLo6GMJuYVXR/Vjy3ARm/dkM+eE\r\n" + 
			"ACPc9Yx2ad3gmntaV2jCt7H0oYExopxGXxwCNILoZOGWy1ZADAGg9hYolD6aoPwQpFy4zhicpTyE\r\n" + 
			"C6nLAoGAZaaC+G4nRt81UJfUcPDCzZGXwv++WOwUFqIPO/11Ub1EoTEH9KStOFHIdf90Q4HQDjLc\r\n" + 
			"Pwm3c71qBNne+3blpyCwSkTQ5fiDoURYe8kDA7moDeaJgfnHaO0FjzrwLl6eiENjmpuSCQKrHBy7\r\n" + 
			"vLn46IPGdO4+Vx4LGhRCJtVZUR8=";
}
