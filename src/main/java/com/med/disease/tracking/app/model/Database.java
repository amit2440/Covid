package com.med.disease.tracking.app.model;

public class Database {
	/** schema */
	private String schema;
	/** table */
	private String table;
	
	public Database(String schema, String table) {
		super();
		this.schema = schema;
		this.table = table;
	}

	/**
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @param schema the schema to set
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}
}
