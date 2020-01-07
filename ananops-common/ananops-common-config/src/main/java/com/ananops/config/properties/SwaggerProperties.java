

package com.ananops.config.properties;

import lombok.Data;

/**
 * The class Async task properties.
 *
 * @author ananops.net @gmail.com
 */
@Data
public class SwaggerProperties {

	private String title;

	private String description;

	private String version = "1.0";

	private String license = "Apache License 2.0";

	private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

	private String contactName = "Ananops";

	private String contactUrl = "http://ananops.com";

	private String contactEmail = "ananops.net@gmail.com";
}
