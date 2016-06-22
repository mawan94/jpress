/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 *
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.core.db;

import com.jfinal.kit.PropKit;

public class DbDialectFactory {

	static DbDialect dialect;

	public static DbDialect getDbDialect() {

		if (dialect == null) {
			dialect = getDialectFromConfig();
		}

		return dialect;
	}

	private static DbDialect getDialectFromConfig() {
		
		String dialect = PropKit.get("db_dialect");
		// 目前只支持 mysql
		if ("mysql".equalsIgnoreCase(dialect)) {
			try {
				Class clazz = Class.forName("org.jpress.db.mysql.MysqlDialect");
				return (DbDialect) clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return null;
	}

}