// Description: Java 11 PostgreSQL Jdbc DbIO implementation for SchemaDef.

/*
 *	server.markhome.msscf.CFBam
 *
 *	Copyright (c) 2020-2025 Mark Stephen Sobkow
 *	
 *
 *	Manufactured by MSS Code Factory 2.13
 */

package server.markhome.msscf.cfbam.CFBamPgSql;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.msscf.msscf.cflib.CFLib.*;
import server.markhome.msscf.cfsec.CFSec.*;
import server.markhome.msscf.cfint.CFInt.*;
import server.markhome.msscf.cfbam.CFBam.*;
import server.markhome.msscf.cfsec.CFSecObj.*;
import server.markhome.msscf.cfint.CFIntObj.*;
import server.markhome.msscf.cfbam.CFBamObj.*;

/*
 *	CFBamPgSqlSchemaDefTable PostgreSQL Jdbc DbIO implementation
 *	for SchemaDef.
 */
public class CFBamPgSqlSchemaDefTable
	implements ICFBamSchemaDefTable
{
	private CFBamPgSqlSchema schema;
	protected PreparedStatement stmtReadBuffByPKey = null;
	protected PreparedStatement stmtReadBuffAll = null;
	protected PreparedStatement stmtLockBuffByPKey = null;
	protected PreparedStatement stmtCreateByPKey = null;
	protected PreparedStatement stmtUpdateByPKey = null;
	protected PreparedStatement stmtDeleteByPKey = null;
	protected PreparedStatement stmtReadAllBuff = null;
	protected PreparedStatement stmtReadBuffByIdIdx = null;
	protected PreparedStatement stmtReadBuffByTenantIdx = null;
	protected PreparedStatement stmtReadBuffByCTenantIdx = null;
	protected PreparedStatement stmtReadBuffByMinorVersionIdx = null;
	protected PreparedStatement stmtReadBuffByUNameIdx = null;
	protected PreparedStatement stmtReadBuffByAuthEMailIdx = null;
	protected PreparedStatement stmtReadBuffByProjectURLIdx = null;
	protected PreparedStatement stmtReadBuffByPubURIIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByTenantIdx = null;
	protected PreparedStatement stmtDeleteByCTenantIdx = null;
	protected PreparedStatement stmtDeleteByMinorVersionIdx = null;
	protected PreparedStatement stmtDeleteByUNameIdx = null;
	protected PreparedStatement stmtDeleteByAuthEMailIdx = null;
	protected PreparedStatement stmtDeleteByProjectURLIdx = null;
	protected PreparedStatement stmtDeleteByPubURIIdx = null;

	public CFBamPgSqlSchemaDefTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createSchemaDef( CFSecAuthorization Authorization,
		CFBamSchemaDefBuff Buff )
	{
		final String S_ProcName = "createSchemaDef";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long MinorVersionId = Buff.getRequiredMinorVersionId();
			String Name = Buff.getRequiredName();
			String DbName = Buff.getOptionalDbName();
			String ShortName = Buff.getOptionalShortName();
			String Label = Buff.getOptionalLabel();
			String ShortDescription = Buff.getOptionalShortDescription();
			String Description = Buff.getOptionalDescription();
			String PublicLicenseName = Buff.getRequiredPublicLicenseName();
			String PublicLicenseText = Buff.getRequiredPublicLicenseText();
			String ImplementationLicenseName = Buff.getOptionalImplementationLicenseName();
			String ImplementationLicenseText = Buff.getOptionalImplementationLicenseText();
			String CopyrightPeriod = Buff.getRequiredCopyrightPeriod();
			String CopyrightHolder = Buff.getRequiredCopyrightHolder();
			String AuthorEMail = Buff.getRequiredAuthorEMail();
			String ProjectURL = Buff.getRequiredProjectURL();
			String PublishURI = Buff.getRequiredPublishURI();
			String JSchemaObjImport = Buff.getOptionalJSchemaObjImport();
			String JSchemaObjInterface = Buff.getOptionalJSchemaObjInterface();
			String JSchemaObjMembers = Buff.getOptionalJSchemaObjMembers();
			String JSchemaObjImplementation = Buff.getOptionalJSchemaObjImplementation();
			String JDb2LUWSchemaObjMembers = Buff.getOptionalJDb2LUWSchemaObjMembers();
			String JDb2LUWSchemaObjImpl = Buff.getOptionalJDb2LUWSchemaObjImpl();
			String JDb2LUWSchemaObjImport = Buff.getOptionalJDb2LUWSchemaObjImport();
			String JMSSqlSchemaObjMembers = Buff.getOptionalJMSSqlSchemaObjMembers();
			String JMSSqlSchemaObjImpl = Buff.getOptionalJMSSqlSchemaObjImpl();
			String JMSSqlSchemaObjImport = Buff.getOptionalJMSSqlSchemaObjImport();
			String JMySqlSchemaObjMembers = Buff.getOptionalJMySqlSchemaObjMembers();
			String JMySqlSchemaObjImpl = Buff.getOptionalJMySqlSchemaObjImpl();
			String JMySqlSchemaObjImport = Buff.getOptionalJMySqlSchemaObjImport();
			String JOracleSchemaObjMembers = Buff.getOptionalJOracleSchemaObjMembers();
			String JOracleSchemaObjImpl = Buff.getOptionalJOracleSchemaObjImpl();
			String JOracleSchemaObjImport = Buff.getOptionalJOracleSchemaObjImport();
			String JPgSqlSchemaObjMembers = Buff.getOptionalJPgSqlSchemaObjMembers();
			String JPgSqlSchemaObjImpl = Buff.getOptionalJPgSqlSchemaObjImpl();
			String JPgSqlSchemaObjImport = Buff.getOptionalJPgSqlSchemaObjImport();
			String JRamSchemaObjMembers = Buff.getOptionalJRamSchemaObjMembers();
			String JRamSchemaObjImpl = Buff.getOptionalJRamSchemaObjImpl();
			String JRamSchemaObjImport = Buff.getOptionalJRamSchemaObjImport();
			String JXMsgSchemaImport = Buff.getOptionalJXMsgSchemaImport();
			String JXMsgSchemaFormatters = Buff.getOptionalJXMsgSchemaFormatters();
			String JXMsgClientSchemaImport = Buff.getOptionalJXMsgClientSchemaImport();
			String JXMsgClientSchemaBody = Buff.getOptionalJXMsgClientSchemaBody();
			String JXMsgRqstSchemaBody = Buff.getOptionalJXMsgRqstSchemaBody();
			String JXMsgRqstSchemaImport = Buff.getOptionalJXMsgRqstSchemaImport();
			String JXMsgRqstSchemaWireParsers = Buff.getOptionalJXMsgRqstSchemaWireParsers();
			String JXMsgRqstSchemaXsdSpec = Buff.getOptionalJXMsgRqstSchemaXsdSpec();
			String JXMsgRqstSchemaXsdElementList = Buff.getOptionalJXMsgRqstSchemaXsdElementList();
			String JXMsgRspnSchemaBody = Buff.getOptionalJXMsgRspnSchemaBody();
			String JXMsgRspnSchemaImport = Buff.getOptionalJXMsgRspnSchemaImport();
			String JXMsgRspnSchemaWireParsers = Buff.getOptionalJXMsgRspnSchemaWireParsers();
			String JXMsgRspnSchemaXsdElementList = Buff.getOptionalJXMsgRspnSchemaXsdElementList();
			String JXMsgRspnSchemaXsdSpec = Buff.getOptionalJXMsgRspnSchemaXsdSpec();
			String CppSchemaObjInclude = Buff.getOptionalCppSchemaObjInclude();
			String CppSchemaObjInterface = Buff.getOptionalCppSchemaObjInterface();
			String CppSchemaObjMembers = Buff.getOptionalCppSchemaObjMembers();
			String CppSchemaObjImplementation = Buff.getOptionalCppSchemaObjImplementation();
			String CppDb2LUWSchemaObjMembers = Buff.getOptionalCppDb2LUWSchemaObjMembers();
			String CppDb2LUWSchemaObjImpl = Buff.getOptionalCppDb2LUWSchemaObjImpl();
			String CppDb2LUWSchemaObjInclude = Buff.getOptionalCppDb2LUWSchemaObjInclude();
			String CppMSSqlSchemaObjMembers = Buff.getOptionalCppMSSqlSchemaObjMembers();
			String CppMSSqlSchemaObjImpl = Buff.getOptionalCppMSSqlSchemaObjImpl();
			String CppMSSqlSchemaObjInclude = Buff.getOptionalCppMSSqlSchemaObjInclude();
			String CppMySqlSchemaObjMembers = Buff.getOptionalCppMySqlSchemaObjMembers();
			String CppMySqlSchemaObjImpl = Buff.getOptionalCppMySqlSchemaObjImpl();
			String CppMySqlSchemaObjInclude = Buff.getOptionalCppMySqlSchemaObjInclude();
			String CppOracleSchemaObjMembers = Buff.getOptionalCppOracleSchemaObjMembers();
			String CppOracleSchemaObjImpl = Buff.getOptionalCppOracleSchemaObjImpl();
			String CppOracleSchemaObjInclude = Buff.getOptionalCppOracleSchemaObjInclude();
			String CppPgSqlSchemaObjMembers = Buff.getOptionalCppPgSqlSchemaObjMembers();
			String CppPgSqlSchemaObjImpl = Buff.getOptionalCppPgSqlSchemaObjImpl();
			String CppPgSqlSchemaObjInclude = Buff.getOptionalCppPgSqlSchemaObjInclude();
			String CppRamSchemaObjMembers = Buff.getOptionalCppRamSchemaObjMembers();
			String CppRamSchemaObjImpl = Buff.getOptionalCppRamSchemaObjImpl();
			String CppRamSchemaObjInclude = Buff.getOptionalCppRamSchemaObjInclude();
			String CppXMsgSchemaInclude = Buff.getOptionalCppXMsgSchemaInclude();
			String CppXMsgSchemaFormatters = Buff.getOptionalCppXMsgSchemaFormatters();
			String CppXMsgClientSchemaInclude = Buff.getOptionalCppXMsgClientSchemaInclude();
			String CppXMsgClientSchemaBody = Buff.getOptionalCppXMsgClientSchemaBody();
			String CppXMsgRqstSchemaBody = Buff.getOptionalCppXMsgRqstSchemaBody();
			String CppXMsgRqstSchemaInclude = Buff.getOptionalCppXMsgRqstSchemaInclude();
			String CppXMsgRqstSchemaWireParsers = Buff.getOptionalCppXMsgRqstSchemaWireParsers();
			String CppXMsgRqstSchemaXsdSpec = Buff.getOptionalCppXMsgRqstSchemaXsdSpec();
			String CppXMsgRqstSchemaXsdElementList = Buff.getOptionalCppXMsgRqstSchemaXsdElementList();
			String CppXMsgRspnSchemaBody = Buff.getOptionalCppXMsgRspnSchemaBody();
			String CppXMsgRspnSchemaInclude = Buff.getOptionalCppXMsgRspnSchemaInclude();
			String CppXMsgRspnSchemaWireParsers = Buff.getOptionalCppXMsgRspnSchemaWireParsers();
			String CppXMsgRspnSchemaXsdElementList = Buff.getOptionalCppXMsgRspnSchemaXsdElementList();
			String CppXMsgRspnSchemaXsdSpec = Buff.getOptionalCppXMsgRspnSchemaXsdSpec();
			String HppSchemaObjInclude = Buff.getOptionalHppSchemaObjInclude();
			String HppSchemaObjInterface = Buff.getOptionalHppSchemaObjInterface();
			String HppSchemaObjMembers = Buff.getOptionalHppSchemaObjMembers();
			String HppSchemaObjImplementation = Buff.getOptionalHppSchemaObjImplementation();
			String HppDb2LUWSchemaObjMembers = Buff.getOptionalHppDb2LUWSchemaObjMembers();
			String HppDb2LUWSchemaObjImpl = Buff.getOptionalHppDb2LUWSchemaObjImpl();
			String HppDb2LUWSchemaObjInclude = Buff.getOptionalHppDb2LUWSchemaObjInclude();
			String HppMSSqlSchemaObjMembers = Buff.getOptionalHppMSSqlSchemaObjMembers();
			String HppMSSqlSchemaObjImpl = Buff.getOptionalHppMSSqlSchemaObjImpl();
			String HppMSSqlSchemaObjInclude = Buff.getOptionalHppMSSqlSchemaObjInclude();
			String HppMySqlSchemaObjMembers = Buff.getOptionalHppMySqlSchemaObjMembers();
			String HppMySqlSchemaObjImpl = Buff.getOptionalHppMySqlSchemaObjImpl();
			String HppMySqlSchemaObjInclude = Buff.getOptionalHppMySqlSchemaObjInclude();
			String HppOracleSchemaObjMembers = Buff.getOptionalHppOracleSchemaObjMembers();
			String HppOracleSchemaObjImpl = Buff.getOptionalHppOracleSchemaObjImpl();
			String HppOracleSchemaObjInclude = Buff.getOptionalHppOracleSchemaObjInclude();
			String HppPgSqlSchemaObjMembers = Buff.getOptionalHppPgSqlSchemaObjMembers();
			String HppPgSqlSchemaObjImpl = Buff.getOptionalHppPgSqlSchemaObjImpl();
			String HppPgSqlSchemaObjInclude = Buff.getOptionalHppPgSqlSchemaObjInclude();
			String HppRamSchemaObjMembers = Buff.getOptionalHppRamSchemaObjMembers();
			String HppRamSchemaObjImpl = Buff.getOptionalHppRamSchemaObjImpl();
			String HppRamSchemaObjInclude = Buff.getOptionalHppRamSchemaObjInclude();
			String HppXMsgSchemaInclude = Buff.getOptionalHppXMsgSchemaInclude();
			String HppXMsgSchemaFormatters = Buff.getOptionalHppXMsgSchemaFormatters();
			String HppXMsgClientSchemaInclude = Buff.getOptionalHppXMsgClientSchemaInclude();
			String HppXMsgClientSchemaBody = Buff.getOptionalHppXMsgClientSchemaBody();
			String HppXMsgRqstSchemaBody = Buff.getOptionalHppXMsgRqstSchemaBody();
			String HppXMsgRqstSchemaInclude = Buff.getOptionalHppXMsgRqstSchemaInclude();
			String HppXMsgRqstSchemaWireParsers = Buff.getOptionalHppXMsgRqstSchemaWireParsers();
			String HppXMsgRqstSchemaXsdSpec = Buff.getOptionalHppXMsgRqstSchemaXsdSpec();
			String HppXMsgRqstSchemaXsdElementList = Buff.getOptionalHppXMsgRqstSchemaXsdElementList();
			String HppXMsgRspnSchemaBody = Buff.getOptionalHppXMsgRspnSchemaBody();
			String HppXMsgRspnSchemaInclude = Buff.getOptionalHppXMsgRspnSchemaInclude();
			String HppXMsgRspnSchemaWireParsers = Buff.getOptionalHppXMsgRspnSchemaWireParsers();
			String HppXMsgRspnSchemaXsdElementList = Buff.getOptionalHppXMsgRspnSchemaXsdElementList();
			String HppXMsgRspnSchemaXsdSpec = Buff.getOptionalHppXMsgRspnSchemaXsdSpec();
			String CsSchemaObjUsing = Buff.getOptionalCsSchemaObjUsing();
			String CsSchemaObjInterface = Buff.getOptionalCsSchemaObjInterface();
			String CsSchemaObjMembers = Buff.getOptionalCsSchemaObjMembers();
			String CsSchemaObjImplementation = Buff.getOptionalCsSchemaObjImplementation();
			String CsDb2LUWSchemaObjMembers = Buff.getOptionalCsDb2LUWSchemaObjMembers();
			String CsDb2LUWSchemaObjImpl = Buff.getOptionalCsDb2LUWSchemaObjImpl();
			String CsDb2LUWSchemaObjUsing = Buff.getOptionalCsDb2LUWSchemaObjUsing();
			String CsMSSqlSchemaObjMembers = Buff.getOptionalCsMSSqlSchemaObjMembers();
			String CsMSSqlSchemaObjImpl = Buff.getOptionalCsMSSqlSchemaObjImpl();
			String CsMSSqlSchemaObjUsing = Buff.getOptionalCsMSSqlSchemaObjUsing();
			String CsMySqlSchemaObjMembers = Buff.getOptionalCsMySqlSchemaObjMembers();
			String CsMySqlSchemaObjImpl = Buff.getOptionalCsMySqlSchemaObjImpl();
			String CsMySqlSchemaObjUsing = Buff.getOptionalCsMySqlSchemaObjUsing();
			String CsOracleSchemaObjMembers = Buff.getOptionalCsOracleSchemaObjMembers();
			String CsOracleSchemaObjImpl = Buff.getOptionalCsOracleSchemaObjImpl();
			String CsOracleSchemaObjUsing = Buff.getOptionalCsOracleSchemaObjUsing();
			String CsPgSqlSchemaObjMembers = Buff.getOptionalCsPgSqlSchemaObjMembers();
			String CsPgSqlSchemaObjImpl = Buff.getOptionalCsPgSqlSchemaObjImpl();
			String CsPgSqlSchemaObjUsing = Buff.getOptionalCsPgSqlSchemaObjUsing();
			String CsRamSchemaObjMembers = Buff.getOptionalCsRamSchemaObjMembers();
			String CsRamSchemaObjImpl = Buff.getOptionalCsRamSchemaObjImpl();
			String CsRamSchemaObjUsing = Buff.getOptionalCsRamSchemaObjUsing();
			String CsXMsgSchemaUsing = Buff.getOptionalCsXMsgSchemaUsing();
			String CsXMsgSchemaFormatters = Buff.getOptionalCsXMsgSchemaFormatters();
			String CsXMsgClientSchemaUsing = Buff.getOptionalCsXMsgClientSchemaUsing();
			String CsXMsgClientSchemaBody = Buff.getOptionalCsXMsgClientSchemaBody();
			String CsXMsgRqstSchemaBody = Buff.getOptionalCsXMsgRqstSchemaBody();
			String CsXMsgRqstSchemaUsing = Buff.getOptionalCsXMsgRqstSchemaUsing();
			String CsXMsgRqstSchemaWireParsers = Buff.getOptionalCsXMsgRqstSchemaWireParsers();
			String CsXMsgRqstSchemaXsdSpec = Buff.getOptionalCsXMsgRqstSchemaXsdSpec();
			String CsXMsgRqstSchemaXsdElementList = Buff.getOptionalCsXMsgRqstSchemaXsdElementList();
			String CsXMsgRspnSchemaBody = Buff.getOptionalCsXMsgRspnSchemaBody();
			String CsXMsgRspnSchemaUsing = Buff.getOptionalCsXMsgRspnSchemaUsing();
			String CsXMsgRspnSchemaWireParsers = Buff.getOptionalCsXMsgRspnSchemaWireParsers();
			String CsXMsgRspnSchemaXsdElementList = Buff.getOptionalCsXMsgRspnSchemaXsdElementList();
			String CsXMsgRspnSchemaXsdSpec = Buff.getOptionalCsXMsgRspnSchemaXsdSpec();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_schemadef( ?, ?, ?, ?, ?, ?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"cast( ? as text )" + ", "
				+		"?" + ", "
				+		"cast( ? as text )" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + " )";
			if( stmtCreateByPKey == null ) {
				stmtCreateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtCreateByPKey.setString( argIdx++, ClassCode );
			stmtCreateByPKey.setLong( argIdx++, TenantId );
			stmtCreateByPKey.setLong( argIdx++, MinorVersionId );
			stmtCreateByPKey.setString( argIdx++, Name );
			if( DbName != null ) {
				stmtCreateByPKey.setString( argIdx++, DbName );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( ShortName != null ) {
				stmtCreateByPKey.setString( argIdx++, ShortName );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Label != null ) {
				stmtCreateByPKey.setString( argIdx++, Label );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( ShortDescription != null ) {
				stmtCreateByPKey.setString( argIdx++, ShortDescription );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Description != null ) {
				stmtCreateByPKey.setString( argIdx++, Description );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtCreateByPKey.setString( argIdx++, PublicLicenseName );
			stmtCreateByPKey.setString( argIdx++, PublicLicenseText );
			if( ImplementationLicenseName != null ) {
				stmtCreateByPKey.setString( argIdx++, ImplementationLicenseName );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( ImplementationLicenseText != null ) {
				stmtCreateByPKey.setString( argIdx++, ImplementationLicenseText );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtCreateByPKey.setString( argIdx++, CopyrightPeriod );
			stmtCreateByPKey.setString( argIdx++, CopyrightHolder );
			stmtCreateByPKey.setString( argIdx++, AuthorEMail );
			stmtCreateByPKey.setString( argIdx++, ProjectURL );
			stmtCreateByPKey.setString( argIdx++, PublishURI );
			if( JSchemaObjImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JSchemaObjImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JSchemaObjInterface != null ) {
				stmtCreateByPKey.setString( argIdx++, JSchemaObjInterface );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, JSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JSchemaObjImplementation != null ) {
				stmtCreateByPKey.setString( argIdx++, JSchemaObjImplementation );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JDb2LUWSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, JDb2LUWSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JDb2LUWSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, JDb2LUWSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JDb2LUWSchemaObjImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JDb2LUWSchemaObjImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMSSqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, JMSSqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMSSqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, JMSSqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMSSqlSchemaObjImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JMSSqlSchemaObjImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMySqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, JMySqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMySqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, JMySqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMySqlSchemaObjImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JMySqlSchemaObjImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JOracleSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, JOracleSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JOracleSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, JOracleSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JOracleSchemaObjImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JOracleSchemaObjImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JPgSqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, JPgSqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JPgSqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, JPgSqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JPgSqlSchemaObjImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JPgSqlSchemaObjImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JRamSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, JRamSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JRamSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, JRamSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JRamSchemaObjImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JRamSchemaObjImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgSchemaImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgSchemaImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgSchemaFormatters != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgSchemaFormatters );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgClientSchemaImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgClientSchemaImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgClientSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgClientSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRqstSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgRqstSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRqstSchemaImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgRqstSchemaImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRqstSchemaWireParsers != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgRqstSchemaWireParsers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRqstSchemaXsdSpec != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgRqstSchemaXsdSpec );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRqstSchemaXsdElementList != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgRqstSchemaXsdElementList );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRspnSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgRspnSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRspnSchemaImport != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgRspnSchemaImport );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRspnSchemaWireParsers != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgRspnSchemaWireParsers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRspnSchemaXsdElementList != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgRspnSchemaXsdElementList );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRspnSchemaXsdSpec != null ) {
				stmtCreateByPKey.setString( argIdx++, JXMsgRspnSchemaXsdSpec );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppSchemaObjInterface != null ) {
				stmtCreateByPKey.setString( argIdx++, CppSchemaObjInterface );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CppSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppSchemaObjImplementation != null ) {
				stmtCreateByPKey.setString( argIdx++, CppSchemaObjImplementation );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppDb2LUWSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CppDb2LUWSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppDb2LUWSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CppDb2LUWSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppDb2LUWSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppDb2LUWSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMSSqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CppMSSqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMSSqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CppMSSqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMSSqlSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppMSSqlSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMySqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CppMySqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMySqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CppMySqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMySqlSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppMySqlSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppOracleSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CppOracleSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppOracleSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CppOracleSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppOracleSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppOracleSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppPgSqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CppPgSqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppPgSqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CppPgSqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppPgSqlSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppPgSqlSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppRamSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CppRamSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppRamSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CppRamSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppRamSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppRamSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgSchemaInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgSchemaInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgSchemaFormatters != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgSchemaFormatters );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgClientSchemaInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgClientSchemaInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgClientSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgClientSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRqstSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgRqstSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRqstSchemaInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgRqstSchemaInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRqstSchemaWireParsers != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgRqstSchemaWireParsers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRqstSchemaXsdSpec != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgRqstSchemaXsdSpec );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRqstSchemaXsdElementList != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgRqstSchemaXsdElementList );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRspnSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgRspnSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRspnSchemaInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgRspnSchemaInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRspnSchemaWireParsers != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgRspnSchemaWireParsers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRspnSchemaXsdElementList != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgRspnSchemaXsdElementList );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRspnSchemaXsdSpec != null ) {
				stmtCreateByPKey.setString( argIdx++, CppXMsgRspnSchemaXsdSpec );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppSchemaObjInterface != null ) {
				stmtCreateByPKey.setString( argIdx++, HppSchemaObjInterface );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, HppSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppSchemaObjImplementation != null ) {
				stmtCreateByPKey.setString( argIdx++, HppSchemaObjImplementation );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppDb2LUWSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, HppDb2LUWSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppDb2LUWSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, HppDb2LUWSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppDb2LUWSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppDb2LUWSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMSSqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, HppMSSqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMSSqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, HppMSSqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMSSqlSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppMSSqlSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMySqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, HppMySqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMySqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, HppMySqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMySqlSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppMySqlSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppOracleSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, HppOracleSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppOracleSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, HppOracleSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppOracleSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppOracleSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppPgSqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, HppPgSqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppPgSqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, HppPgSqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppPgSqlSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppPgSqlSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppRamSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, HppRamSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppRamSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, HppRamSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppRamSchemaObjInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppRamSchemaObjInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgSchemaInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgSchemaInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgSchemaFormatters != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgSchemaFormatters );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgClientSchemaInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgClientSchemaInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgClientSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgClientSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRqstSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgRqstSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRqstSchemaInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgRqstSchemaInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRqstSchemaWireParsers != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgRqstSchemaWireParsers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRqstSchemaXsdSpec != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgRqstSchemaXsdSpec );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRqstSchemaXsdElementList != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgRqstSchemaXsdElementList );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRspnSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgRspnSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRspnSchemaInclude != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgRspnSchemaInclude );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRspnSchemaWireParsers != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgRspnSchemaWireParsers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRspnSchemaXsdElementList != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgRspnSchemaXsdElementList );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRspnSchemaXsdSpec != null ) {
				stmtCreateByPKey.setString( argIdx++, HppXMsgRspnSchemaXsdSpec );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsSchemaObjUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsSchemaObjUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsSchemaObjInterface != null ) {
				stmtCreateByPKey.setString( argIdx++, CsSchemaObjInterface );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CsSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsSchemaObjImplementation != null ) {
				stmtCreateByPKey.setString( argIdx++, CsSchemaObjImplementation );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsDb2LUWSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CsDb2LUWSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsDb2LUWSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CsDb2LUWSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsDb2LUWSchemaObjUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsDb2LUWSchemaObjUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMSSqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CsMSSqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMSSqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CsMSSqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMSSqlSchemaObjUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsMSSqlSchemaObjUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMySqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CsMySqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMySqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CsMySqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMySqlSchemaObjUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsMySqlSchemaObjUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsOracleSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CsOracleSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsOracleSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CsOracleSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsOracleSchemaObjUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsOracleSchemaObjUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsPgSqlSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CsPgSqlSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsPgSqlSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CsPgSqlSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsPgSqlSchemaObjUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsPgSqlSchemaObjUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsRamSchemaObjMembers != null ) {
				stmtCreateByPKey.setString( argIdx++, CsRamSchemaObjMembers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsRamSchemaObjImpl != null ) {
				stmtCreateByPKey.setString( argIdx++, CsRamSchemaObjImpl );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsRamSchemaObjUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsRamSchemaObjUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgSchemaUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgSchemaUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgSchemaFormatters != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgSchemaFormatters );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgClientSchemaUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgClientSchemaUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgClientSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgClientSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRqstSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgRqstSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRqstSchemaUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgRqstSchemaUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRqstSchemaWireParsers != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgRqstSchemaWireParsers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRqstSchemaXsdSpec != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgRqstSchemaXsdSpec );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRqstSchemaXsdElementList != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgRqstSchemaXsdElementList );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRspnSchemaBody != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgRspnSchemaBody );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRspnSchemaUsing != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgRspnSchemaUsing );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRspnSchemaWireParsers != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgRspnSchemaWireParsers );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRspnSchemaXsdElementList != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgRspnSchemaXsdElementList );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRspnSchemaXsdSpec != null ) {
				stmtCreateByPKey.setString( argIdx++, CsXMsgRspnSchemaXsdSpec );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamSchemaDefBuff createdBuff = unpackSchemaDefResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredTenantId( createdBuff.getRequiredTenantId() );
				Buff.setRequiredId( createdBuff.getRequiredId() );
				Buff.setRequiredRevision( createdBuff.getRequiredRevision() );
				Buff.setCreatedByUserId( createdBuff.getCreatedByUserId() );
				Buff.setCreatedAt( createdBuff.getCreatedAt() );
				Buff.setUpdatedByUserId( createdBuff.getUpdatedByUserId() );
				Buff.setUpdatedAt( createdBuff.getUpdatedAt() );
				Buff.setRequiredMinorVersionId( createdBuff.getRequiredMinorVersionId() );
				Buff.setRequiredName( createdBuff.getRequiredName() );
				Buff.setOptionalDbName( createdBuff.getOptionalDbName() );
				Buff.setOptionalShortName( createdBuff.getOptionalShortName() );
				Buff.setOptionalLabel( createdBuff.getOptionalLabel() );
				Buff.setOptionalShortDescription( createdBuff.getOptionalShortDescription() );
				Buff.setOptionalDescription( createdBuff.getOptionalDescription() );
				Buff.setRequiredPublicLicenseName( createdBuff.getRequiredPublicLicenseName() );
				Buff.setRequiredPublicLicenseText( createdBuff.getRequiredPublicLicenseText() );
				Buff.setOptionalImplementationLicenseName( createdBuff.getOptionalImplementationLicenseName() );
				Buff.setOptionalImplementationLicenseText( createdBuff.getOptionalImplementationLicenseText() );
				Buff.setRequiredCopyrightPeriod( createdBuff.getRequiredCopyrightPeriod() );
				Buff.setRequiredCopyrightHolder( createdBuff.getRequiredCopyrightHolder() );
				Buff.setRequiredAuthorEMail( createdBuff.getRequiredAuthorEMail() );
				Buff.setRequiredProjectURL( createdBuff.getRequiredProjectURL() );
				Buff.setRequiredPublishURI( createdBuff.getRequiredPublishURI() );
				Buff.setOptionalJSchemaObjImport( createdBuff.getOptionalJSchemaObjImport() );
				Buff.setOptionalJSchemaObjInterface( createdBuff.getOptionalJSchemaObjInterface() );
				Buff.setOptionalJSchemaObjMembers( createdBuff.getOptionalJSchemaObjMembers() );
				Buff.setOptionalJSchemaObjImplementation( createdBuff.getOptionalJSchemaObjImplementation() );
				Buff.setOptionalJDb2LUWSchemaObjMembers( createdBuff.getOptionalJDb2LUWSchemaObjMembers() );
				Buff.setOptionalJDb2LUWSchemaObjImpl( createdBuff.getOptionalJDb2LUWSchemaObjImpl() );
				Buff.setOptionalJDb2LUWSchemaObjImport( createdBuff.getOptionalJDb2LUWSchemaObjImport() );
				Buff.setOptionalJMSSqlSchemaObjMembers( createdBuff.getOptionalJMSSqlSchemaObjMembers() );
				Buff.setOptionalJMSSqlSchemaObjImpl( createdBuff.getOptionalJMSSqlSchemaObjImpl() );
				Buff.setOptionalJMSSqlSchemaObjImport( createdBuff.getOptionalJMSSqlSchemaObjImport() );
				Buff.setOptionalJMySqlSchemaObjMembers( createdBuff.getOptionalJMySqlSchemaObjMembers() );
				Buff.setOptionalJMySqlSchemaObjImpl( createdBuff.getOptionalJMySqlSchemaObjImpl() );
				Buff.setOptionalJMySqlSchemaObjImport( createdBuff.getOptionalJMySqlSchemaObjImport() );
				Buff.setOptionalJOracleSchemaObjMembers( createdBuff.getOptionalJOracleSchemaObjMembers() );
				Buff.setOptionalJOracleSchemaObjImpl( createdBuff.getOptionalJOracleSchemaObjImpl() );
				Buff.setOptionalJOracleSchemaObjImport( createdBuff.getOptionalJOracleSchemaObjImport() );
				Buff.setOptionalJPgSqlSchemaObjMembers( createdBuff.getOptionalJPgSqlSchemaObjMembers() );
				Buff.setOptionalJPgSqlSchemaObjImpl( createdBuff.getOptionalJPgSqlSchemaObjImpl() );
				Buff.setOptionalJPgSqlSchemaObjImport( createdBuff.getOptionalJPgSqlSchemaObjImport() );
				Buff.setOptionalJRamSchemaObjMembers( createdBuff.getOptionalJRamSchemaObjMembers() );
				Buff.setOptionalJRamSchemaObjImpl( createdBuff.getOptionalJRamSchemaObjImpl() );
				Buff.setOptionalJRamSchemaObjImport( createdBuff.getOptionalJRamSchemaObjImport() );
				Buff.setOptionalJXMsgSchemaImport( createdBuff.getOptionalJXMsgSchemaImport() );
				Buff.setOptionalJXMsgSchemaFormatters( createdBuff.getOptionalJXMsgSchemaFormatters() );
				Buff.setOptionalJXMsgClientSchemaImport( createdBuff.getOptionalJXMsgClientSchemaImport() );
				Buff.setOptionalJXMsgClientSchemaBody( createdBuff.getOptionalJXMsgClientSchemaBody() );
				Buff.setOptionalJXMsgRqstSchemaBody( createdBuff.getOptionalJXMsgRqstSchemaBody() );
				Buff.setOptionalJXMsgRqstSchemaImport( createdBuff.getOptionalJXMsgRqstSchemaImport() );
				Buff.setOptionalJXMsgRqstSchemaWireParsers( createdBuff.getOptionalJXMsgRqstSchemaWireParsers() );
				Buff.setOptionalJXMsgRqstSchemaXsdSpec( createdBuff.getOptionalJXMsgRqstSchemaXsdSpec() );
				Buff.setOptionalJXMsgRqstSchemaXsdElementList( createdBuff.getOptionalJXMsgRqstSchemaXsdElementList() );
				Buff.setOptionalJXMsgRspnSchemaBody( createdBuff.getOptionalJXMsgRspnSchemaBody() );
				Buff.setOptionalJXMsgRspnSchemaImport( createdBuff.getOptionalJXMsgRspnSchemaImport() );
				Buff.setOptionalJXMsgRspnSchemaWireParsers( createdBuff.getOptionalJXMsgRspnSchemaWireParsers() );
				Buff.setOptionalJXMsgRspnSchemaXsdElementList( createdBuff.getOptionalJXMsgRspnSchemaXsdElementList() );
				Buff.setOptionalJXMsgRspnSchemaXsdSpec( createdBuff.getOptionalJXMsgRspnSchemaXsdSpec() );
				Buff.setOptionalCppSchemaObjInclude( createdBuff.getOptionalCppSchemaObjInclude() );
				Buff.setOptionalCppSchemaObjInterface( createdBuff.getOptionalCppSchemaObjInterface() );
				Buff.setOptionalCppSchemaObjMembers( createdBuff.getOptionalCppSchemaObjMembers() );
				Buff.setOptionalCppSchemaObjImplementation( createdBuff.getOptionalCppSchemaObjImplementation() );
				Buff.setOptionalCppDb2LUWSchemaObjMembers( createdBuff.getOptionalCppDb2LUWSchemaObjMembers() );
				Buff.setOptionalCppDb2LUWSchemaObjImpl( createdBuff.getOptionalCppDb2LUWSchemaObjImpl() );
				Buff.setOptionalCppDb2LUWSchemaObjInclude( createdBuff.getOptionalCppDb2LUWSchemaObjInclude() );
				Buff.setOptionalCppMSSqlSchemaObjMembers( createdBuff.getOptionalCppMSSqlSchemaObjMembers() );
				Buff.setOptionalCppMSSqlSchemaObjImpl( createdBuff.getOptionalCppMSSqlSchemaObjImpl() );
				Buff.setOptionalCppMSSqlSchemaObjInclude( createdBuff.getOptionalCppMSSqlSchemaObjInclude() );
				Buff.setOptionalCppMySqlSchemaObjMembers( createdBuff.getOptionalCppMySqlSchemaObjMembers() );
				Buff.setOptionalCppMySqlSchemaObjImpl( createdBuff.getOptionalCppMySqlSchemaObjImpl() );
				Buff.setOptionalCppMySqlSchemaObjInclude( createdBuff.getOptionalCppMySqlSchemaObjInclude() );
				Buff.setOptionalCppOracleSchemaObjMembers( createdBuff.getOptionalCppOracleSchemaObjMembers() );
				Buff.setOptionalCppOracleSchemaObjImpl( createdBuff.getOptionalCppOracleSchemaObjImpl() );
				Buff.setOptionalCppOracleSchemaObjInclude( createdBuff.getOptionalCppOracleSchemaObjInclude() );
				Buff.setOptionalCppPgSqlSchemaObjMembers( createdBuff.getOptionalCppPgSqlSchemaObjMembers() );
				Buff.setOptionalCppPgSqlSchemaObjImpl( createdBuff.getOptionalCppPgSqlSchemaObjImpl() );
				Buff.setOptionalCppPgSqlSchemaObjInclude( createdBuff.getOptionalCppPgSqlSchemaObjInclude() );
				Buff.setOptionalCppRamSchemaObjMembers( createdBuff.getOptionalCppRamSchemaObjMembers() );
				Buff.setOptionalCppRamSchemaObjImpl( createdBuff.getOptionalCppRamSchemaObjImpl() );
				Buff.setOptionalCppRamSchemaObjInclude( createdBuff.getOptionalCppRamSchemaObjInclude() );
				Buff.setOptionalCppXMsgSchemaInclude( createdBuff.getOptionalCppXMsgSchemaInclude() );
				Buff.setOptionalCppXMsgSchemaFormatters( createdBuff.getOptionalCppXMsgSchemaFormatters() );
				Buff.setOptionalCppXMsgClientSchemaInclude( createdBuff.getOptionalCppXMsgClientSchemaInclude() );
				Buff.setOptionalCppXMsgClientSchemaBody( createdBuff.getOptionalCppXMsgClientSchemaBody() );
				Buff.setOptionalCppXMsgRqstSchemaBody( createdBuff.getOptionalCppXMsgRqstSchemaBody() );
				Buff.setOptionalCppXMsgRqstSchemaInclude( createdBuff.getOptionalCppXMsgRqstSchemaInclude() );
				Buff.setOptionalCppXMsgRqstSchemaWireParsers( createdBuff.getOptionalCppXMsgRqstSchemaWireParsers() );
				Buff.setOptionalCppXMsgRqstSchemaXsdSpec( createdBuff.getOptionalCppXMsgRqstSchemaXsdSpec() );
				Buff.setOptionalCppXMsgRqstSchemaXsdElementList( createdBuff.getOptionalCppXMsgRqstSchemaXsdElementList() );
				Buff.setOptionalCppXMsgRspnSchemaBody( createdBuff.getOptionalCppXMsgRspnSchemaBody() );
				Buff.setOptionalCppXMsgRspnSchemaInclude( createdBuff.getOptionalCppXMsgRspnSchemaInclude() );
				Buff.setOptionalCppXMsgRspnSchemaWireParsers( createdBuff.getOptionalCppXMsgRspnSchemaWireParsers() );
				Buff.setOptionalCppXMsgRspnSchemaXsdElementList( createdBuff.getOptionalCppXMsgRspnSchemaXsdElementList() );
				Buff.setOptionalCppXMsgRspnSchemaXsdSpec( createdBuff.getOptionalCppXMsgRspnSchemaXsdSpec() );
				Buff.setOptionalHppSchemaObjInclude( createdBuff.getOptionalHppSchemaObjInclude() );
				Buff.setOptionalHppSchemaObjInterface( createdBuff.getOptionalHppSchemaObjInterface() );
				Buff.setOptionalHppSchemaObjMembers( createdBuff.getOptionalHppSchemaObjMembers() );
				Buff.setOptionalHppSchemaObjImplementation( createdBuff.getOptionalHppSchemaObjImplementation() );
				Buff.setOptionalHppDb2LUWSchemaObjMembers( createdBuff.getOptionalHppDb2LUWSchemaObjMembers() );
				Buff.setOptionalHppDb2LUWSchemaObjImpl( createdBuff.getOptionalHppDb2LUWSchemaObjImpl() );
				Buff.setOptionalHppDb2LUWSchemaObjInclude( createdBuff.getOptionalHppDb2LUWSchemaObjInclude() );
				Buff.setOptionalHppMSSqlSchemaObjMembers( createdBuff.getOptionalHppMSSqlSchemaObjMembers() );
				Buff.setOptionalHppMSSqlSchemaObjImpl( createdBuff.getOptionalHppMSSqlSchemaObjImpl() );
				Buff.setOptionalHppMSSqlSchemaObjInclude( createdBuff.getOptionalHppMSSqlSchemaObjInclude() );
				Buff.setOptionalHppMySqlSchemaObjMembers( createdBuff.getOptionalHppMySqlSchemaObjMembers() );
				Buff.setOptionalHppMySqlSchemaObjImpl( createdBuff.getOptionalHppMySqlSchemaObjImpl() );
				Buff.setOptionalHppMySqlSchemaObjInclude( createdBuff.getOptionalHppMySqlSchemaObjInclude() );
				Buff.setOptionalHppOracleSchemaObjMembers( createdBuff.getOptionalHppOracleSchemaObjMembers() );
				Buff.setOptionalHppOracleSchemaObjImpl( createdBuff.getOptionalHppOracleSchemaObjImpl() );
				Buff.setOptionalHppOracleSchemaObjInclude( createdBuff.getOptionalHppOracleSchemaObjInclude() );
				Buff.setOptionalHppPgSqlSchemaObjMembers( createdBuff.getOptionalHppPgSqlSchemaObjMembers() );
				Buff.setOptionalHppPgSqlSchemaObjImpl( createdBuff.getOptionalHppPgSqlSchemaObjImpl() );
				Buff.setOptionalHppPgSqlSchemaObjInclude( createdBuff.getOptionalHppPgSqlSchemaObjInclude() );
				Buff.setOptionalHppRamSchemaObjMembers( createdBuff.getOptionalHppRamSchemaObjMembers() );
				Buff.setOptionalHppRamSchemaObjImpl( createdBuff.getOptionalHppRamSchemaObjImpl() );
				Buff.setOptionalHppRamSchemaObjInclude( createdBuff.getOptionalHppRamSchemaObjInclude() );
				Buff.setOptionalHppXMsgSchemaInclude( createdBuff.getOptionalHppXMsgSchemaInclude() );
				Buff.setOptionalHppXMsgSchemaFormatters( createdBuff.getOptionalHppXMsgSchemaFormatters() );
				Buff.setOptionalHppXMsgClientSchemaInclude( createdBuff.getOptionalHppXMsgClientSchemaInclude() );
				Buff.setOptionalHppXMsgClientSchemaBody( createdBuff.getOptionalHppXMsgClientSchemaBody() );
				Buff.setOptionalHppXMsgRqstSchemaBody( createdBuff.getOptionalHppXMsgRqstSchemaBody() );
				Buff.setOptionalHppXMsgRqstSchemaInclude( createdBuff.getOptionalHppXMsgRqstSchemaInclude() );
				Buff.setOptionalHppXMsgRqstSchemaWireParsers( createdBuff.getOptionalHppXMsgRqstSchemaWireParsers() );
				Buff.setOptionalHppXMsgRqstSchemaXsdSpec( createdBuff.getOptionalHppXMsgRqstSchemaXsdSpec() );
				Buff.setOptionalHppXMsgRqstSchemaXsdElementList( createdBuff.getOptionalHppXMsgRqstSchemaXsdElementList() );
				Buff.setOptionalHppXMsgRspnSchemaBody( createdBuff.getOptionalHppXMsgRspnSchemaBody() );
				Buff.setOptionalHppXMsgRspnSchemaInclude( createdBuff.getOptionalHppXMsgRspnSchemaInclude() );
				Buff.setOptionalHppXMsgRspnSchemaWireParsers( createdBuff.getOptionalHppXMsgRspnSchemaWireParsers() );
				Buff.setOptionalHppXMsgRspnSchemaXsdElementList( createdBuff.getOptionalHppXMsgRspnSchemaXsdElementList() );
				Buff.setOptionalHppXMsgRspnSchemaXsdSpec( createdBuff.getOptionalHppXMsgRspnSchemaXsdSpec() );
				Buff.setOptionalCsSchemaObjUsing( createdBuff.getOptionalCsSchemaObjUsing() );
				Buff.setOptionalCsSchemaObjInterface( createdBuff.getOptionalCsSchemaObjInterface() );
				Buff.setOptionalCsSchemaObjMembers( createdBuff.getOptionalCsSchemaObjMembers() );
				Buff.setOptionalCsSchemaObjImplementation( createdBuff.getOptionalCsSchemaObjImplementation() );
				Buff.setOptionalCsDb2LUWSchemaObjMembers( createdBuff.getOptionalCsDb2LUWSchemaObjMembers() );
				Buff.setOptionalCsDb2LUWSchemaObjImpl( createdBuff.getOptionalCsDb2LUWSchemaObjImpl() );
				Buff.setOptionalCsDb2LUWSchemaObjUsing( createdBuff.getOptionalCsDb2LUWSchemaObjUsing() );
				Buff.setOptionalCsMSSqlSchemaObjMembers( createdBuff.getOptionalCsMSSqlSchemaObjMembers() );
				Buff.setOptionalCsMSSqlSchemaObjImpl( createdBuff.getOptionalCsMSSqlSchemaObjImpl() );
				Buff.setOptionalCsMSSqlSchemaObjUsing( createdBuff.getOptionalCsMSSqlSchemaObjUsing() );
				Buff.setOptionalCsMySqlSchemaObjMembers( createdBuff.getOptionalCsMySqlSchemaObjMembers() );
				Buff.setOptionalCsMySqlSchemaObjImpl( createdBuff.getOptionalCsMySqlSchemaObjImpl() );
				Buff.setOptionalCsMySqlSchemaObjUsing( createdBuff.getOptionalCsMySqlSchemaObjUsing() );
				Buff.setOptionalCsOracleSchemaObjMembers( createdBuff.getOptionalCsOracleSchemaObjMembers() );
				Buff.setOptionalCsOracleSchemaObjImpl( createdBuff.getOptionalCsOracleSchemaObjImpl() );
				Buff.setOptionalCsOracleSchemaObjUsing( createdBuff.getOptionalCsOracleSchemaObjUsing() );
				Buff.setOptionalCsPgSqlSchemaObjMembers( createdBuff.getOptionalCsPgSqlSchemaObjMembers() );
				Buff.setOptionalCsPgSqlSchemaObjImpl( createdBuff.getOptionalCsPgSqlSchemaObjImpl() );
				Buff.setOptionalCsPgSqlSchemaObjUsing( createdBuff.getOptionalCsPgSqlSchemaObjUsing() );
				Buff.setOptionalCsRamSchemaObjMembers( createdBuff.getOptionalCsRamSchemaObjMembers() );
				Buff.setOptionalCsRamSchemaObjImpl( createdBuff.getOptionalCsRamSchemaObjImpl() );
				Buff.setOptionalCsRamSchemaObjUsing( createdBuff.getOptionalCsRamSchemaObjUsing() );
				Buff.setOptionalCsXMsgSchemaUsing( createdBuff.getOptionalCsXMsgSchemaUsing() );
				Buff.setOptionalCsXMsgSchemaFormatters( createdBuff.getOptionalCsXMsgSchemaFormatters() );
				Buff.setOptionalCsXMsgClientSchemaUsing( createdBuff.getOptionalCsXMsgClientSchemaUsing() );
				Buff.setOptionalCsXMsgClientSchemaBody( createdBuff.getOptionalCsXMsgClientSchemaBody() );
				Buff.setOptionalCsXMsgRqstSchemaBody( createdBuff.getOptionalCsXMsgRqstSchemaBody() );
				Buff.setOptionalCsXMsgRqstSchemaUsing( createdBuff.getOptionalCsXMsgRqstSchemaUsing() );
				Buff.setOptionalCsXMsgRqstSchemaWireParsers( createdBuff.getOptionalCsXMsgRqstSchemaWireParsers() );
				Buff.setOptionalCsXMsgRqstSchemaXsdSpec( createdBuff.getOptionalCsXMsgRqstSchemaXsdSpec() );
				Buff.setOptionalCsXMsgRqstSchemaXsdElementList( createdBuff.getOptionalCsXMsgRqstSchemaXsdElementList() );
				Buff.setOptionalCsXMsgRspnSchemaBody( createdBuff.getOptionalCsXMsgRspnSchemaBody() );
				Buff.setOptionalCsXMsgRspnSchemaUsing( createdBuff.getOptionalCsXMsgRspnSchemaUsing() );
				Buff.setOptionalCsXMsgRspnSchemaWireParsers( createdBuff.getOptionalCsXMsgRspnSchemaWireParsers() );
				Buff.setOptionalCsXMsgRspnSchemaXsdElementList( createdBuff.getOptionalCsXMsgRspnSchemaXsdElementList() );
				Buff.setOptionalCsXMsgRspnSchemaXsdSpec( createdBuff.getOptionalCsXMsgRspnSchemaXsdSpec() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public String S_sqlSelectSchemaDefDistinctClassCode = null;

	public String getSqlSelectSchemaDefDistinctClassCode() {
		if( S_sqlSelectSchemaDefDistinctClassCode == null ) {
			S_sqlSelectSchemaDefDistinctClassCode =
					"SELECT "
					+		"DISTINCT a801.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".schemadef a802 ON "
					+		"a802.TenantId = a801.TenantId "
					+		"AND a802.Id = a801.Id ";
		}
		return( S_sqlSelectSchemaDefDistinctClassCode );
	}

	public String S_sqlSelectSchemaDefBuff = null;

	public String getSqlSelectSchemaDefBuff() {
		if( S_sqlSelectSchemaDefBuff == null ) {
			S_sqlSelectSchemaDefBuff =
					"SELECT "
					+		"a801.ClassCode, "
					+		"a801.TenantId, "
					+		"a801.Id, "
					+		"a802.MinorVersionId, "
					+		"a802.safe_name, "
					+		"a802.DbName, "
					+		"a802.short_name, "
					+		"a802.Label, "
					+		"a802.short_descr, "
					+		"a802.descr, "
					+		"a802.pub_lic_nm, "
					+		"a802.pub_lic_txt, "
					+		"a802.impl_lic_nm, "
					+		"a802.impl_lic_txt, "
					+		"a802.CopyPerd, "
					+		"a802.CopyHold, "
					+		"a802.AuthEMail, "
					+		"a802.ProjURL, "
					+		"a802.PubURI, "
					+		"a802.jobj_import, "
					+		"a802.jobj_xface, "
					+		"a802.jobj_memb, "
					+		"a802.jobj_impl, "
					+		"a802.jdb2_memb, "
					+		"a802.jdb2_impl, "
					+		"a802.jdb2_import, "
					+		"a802.jmssql_memb, "
					+		"a802.jmssql_impl, "
					+		"a802.jmssql_import, "
					+		"a802.jmysql_memb, "
					+		"a802.jmysql_impl, "
					+		"a802.jmysql_import, "
					+		"a802.joracle_memb, "
					+		"a802.joracle_impl, "
					+		"a802.joracle_import, "
					+		"a802.jpgsql_memb, "
					+		"a802.jpgsql_impl, "
					+		"a802.jpgsql_import, "
					+		"a802.jram_memb, "
					+		"a802.jram_soimpl, "
					+		"a802.jram_imprt, "
					+		"a802.javaxmsgschimp, "
					+		"a802.javaxmsgschfmts, "
					+		"a802.javaxmsgcltschimp, "
					+		"a802.javaxmsgcltschbdy, "
					+		"a802.javaxmsgrqstschbdy, "
					+		"a802.javaxmsgrqstschimp, "
					+		"a802.javaxmsgrqstschwirep, "
					+		"a802.javaxmsgrqstschxsdspec, "
					+		"a802.javaxmsgrqstschxsdeltlst, "
					+		"a802.javaxmsgrspnschemabody, "
					+		"a802.javaxmsgrspnschimp, "
					+		"a802.javaxmsgrspnschwirep, "
					+		"a802.javaxmsgrspnschxsdeltlst, "
					+		"a802.javaxmsgrspnschxsdspec, "
					+		"a802.cppobj_include, "
					+		"a802.cppobj_xface, "
					+		"a802.cppobj_memb, "
					+		"a802.cppobj_incl, "
					+		"a802.cppdb2_memb, "
					+		"a802.cppdb2_incl, "
					+		"a802.cppdb2_include, "
					+		"a802.cppmssql_memb, "
					+		"a802.cppmssql_incl, "
					+		"a802.cppmssql_include, "
					+		"a802.cppmysql_memb, "
					+		"a802.cppmysql_incl, "
					+		"a802.cppmysql_include, "
					+		"a802.cpporacle_memb, "
					+		"a802.cpporacle_incl, "
					+		"a802.cpporacle_include, "
					+		"a802.cpppgsql_memb, "
					+		"a802.cpppgsql_incl, "
					+		"a802.cpppgsql_include, "
					+		"a802.cppram_memb, "
					+		"a802.cppram_soincl, "
					+		"a802.cppram_incrt, "
					+		"a802.cppxmsgschinc, "
					+		"a802.cppxmsgschfmts, "
					+		"a802.cppxmsgcltschinc, "
					+		"a802.cppxmsgcltschbdy, "
					+		"a802.cppxmsgrqstschbdy, "
					+		"a802.cppxmsgrqstschinc, "
					+		"a802.cppxmsgrqstschwirep, "
					+		"a802.cppxmsgrqstschxsdspec, "
					+		"a802.cppxmsgrqstschxsdeltlst, "
					+		"a802.cppxmsgrspnschemabody, "
					+		"a802.cppxmsgrspnschinc, "
					+		"a802.cppxmsgrspnschwirep, "
					+		"a802.cppxmsgrspnschxsdeltlst, "
					+		"a802.cppxmsgrspnschxsdspec, "
					+		"a802.hppobj_include, "
					+		"a802.hppobj_xface, "
					+		"a802.hppobj_memb, "
					+		"a802.hppobj_incl, "
					+		"a802.hppdb2_memb, "
					+		"a802.hppdb2_incl, "
					+		"a802.hppdb2_include, "
					+		"a802.hppmssql_memb, "
					+		"a802.hppmssql_incl, "
					+		"a802.hppmssql_include, "
					+		"a802.hppmysql_memb, "
					+		"a802.hppmysql_incl, "
					+		"a802.hppmysql_include, "
					+		"a802.hpporacle_memb, "
					+		"a802.hpporacle_incl, "
					+		"a802.hpporacle_include, "
					+		"a802.hpppgsql_memb, "
					+		"a802.hpppgsql_incl, "
					+		"a802.hpppgsql_include, "
					+		"a802.hppram_memb, "
					+		"a802.hppram_soincl, "
					+		"a802.hppram_incrt, "
					+		"a802.hppxmsgschinc, "
					+		"a802.hppxmsgschfmts, "
					+		"a802.hppxmsgcltschinc, "
					+		"a802.hppxmsgcltschbdy, "
					+		"a802.hppxmsgrqstschbdy, "
					+		"a802.hppxmsgrqstschinc, "
					+		"a802.hppxmsgrqstschwirep, "
					+		"a802.hppxmsgrqstschxsdspec, "
					+		"a802.hppxmsgrqstschxsdeltlst, "
					+		"a802.hppxmsgrspnschemabody, "
					+		"a802.hppxmsgrspnschinc, "
					+		"a802.hppxmsgrspnschwirep, "
					+		"a802.hppxmsgrspnschxsdeltlst, "
					+		"a802.hppxmsgrspnschxsdspec, "
					+		"a802.csobj_using, "
					+		"a802.csobj_xface, "
					+		"a802.csobj_memb, "
					+		"a802.csobj_incl, "
					+		"a802.csdb2_memb, "
					+		"a802.csdb2_incl, "
					+		"a802.csdb2_using, "
					+		"a802.csmssql_memb, "
					+		"a802.csmssql_incl, "
					+		"a802.csmssql_using, "
					+		"a802.csmysql_memb, "
					+		"a802.csmysql_incl, "
					+		"a802.csmysql_using, "
					+		"a802.csoracle_memb, "
					+		"a802.csoracle_incl, "
					+		"a802.csoracle_using, "
					+		"a802.cspgsql_memb, "
					+		"a802.cspgsql_incl, "
					+		"a802.cspgsql_using, "
					+		"a802.csram_memb, "
					+		"a802.csram_soincl, "
					+		"a802.csram_incrt, "
					+		"a802.csxmsgschinc, "
					+		"a802.csxmsgschfmts, "
					+		"a802.csxmsgcltschinc, "
					+		"a802.csxmsgcltschbdy, "
					+		"a802.csxmsgrqstschbdy, "
					+		"a802.csxmsgrqstschinc, "
					+		"a802.csxmsgrqstschwirep, "
					+		"a802.csxmsgrqstschxsdspec, "
					+		"a802.csxmsgrqstschxsdeltlst, "
					+		"a802.csxmsgrspnschemabody, "
					+		"a802.csxmsgrspnschinc, "
					+		"a802.csxmsgrspnschwirep, "
					+		"a802.csxmsgrspnschxsdeltlst, "
					+		"a802.csxmsgrspnschxsdspec, "
					+		"a801.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".schemadef a802 ON "
					+		"a802.TenantId = a801.TenantId "
					+		"AND a802.Id = a801.Id ";
		}
		return( S_sqlSelectSchemaDefBuff );
	}

	protected CFBamSchemaDefBuff unpackSchemaDefResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackSchemaDefResultSetToBuff";
		int idxcol = 1;
		String classCode = resultSet.getString( idxcol );
		idxcol++;
		CFBamSchemaDefBuff buff;
		if( classCode.equals( "a802" ) ) {
			buff = schema.getFactorySchemaDef().newBuff();
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Unrecognized class code \"" + classCode + "\"" );
		}
		{
			String colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedByUserId( null );
			}
			else {
				buff.setCreatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedAt( null );
			}
			else {
				buff.setCreatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedByUserId( null );
			}
			else {
				buff.setUpdatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedAt( null );
			}
			else {
				buff.setUpdatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
		}
		buff.setRequiredTenantId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredMinorVersionId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredName( resultSet.getString( idxcol ) );
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDbName( null );
			}
			else {
				buff.setOptionalDbName( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalShortName( null );
			}
			else {
				buff.setOptionalShortName( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalLabel( null );
			}
			else {
				buff.setOptionalLabel( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalShortDescription( null );
			}
			else {
				buff.setOptionalShortDescription( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDescription( null );
			}
			else {
				buff.setOptionalDescription( colVal );
			}
		}
		idxcol++;
		buff.setRequiredPublicLicenseName( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredPublicLicenseText( resultSet.getString( idxcol ) );
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalImplementationLicenseName( null );
			}
			else {
				buff.setOptionalImplementationLicenseName( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalImplementationLicenseText( null );
			}
			else {
				buff.setOptionalImplementationLicenseText( colVal );
			}
		}
		idxcol++;
		buff.setRequiredCopyrightPeriod( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredCopyrightHolder( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredAuthorEMail( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredProjectURL( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredPublishURI( resultSet.getString( idxcol ) );
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJSchemaObjImport( null );
			}
			else {
				buff.setOptionalJSchemaObjImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJSchemaObjInterface( null );
			}
			else {
				buff.setOptionalJSchemaObjInterface( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJSchemaObjMembers( null );
			}
			else {
				buff.setOptionalJSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJSchemaObjImplementation( null );
			}
			else {
				buff.setOptionalJSchemaObjImplementation( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJDb2LUWSchemaObjMembers( null );
			}
			else {
				buff.setOptionalJDb2LUWSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJDb2LUWSchemaObjImpl( null );
			}
			else {
				buff.setOptionalJDb2LUWSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJDb2LUWSchemaObjImport( null );
			}
			else {
				buff.setOptionalJDb2LUWSchemaObjImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJMSSqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalJMSSqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJMSSqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalJMSSqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJMSSqlSchemaObjImport( null );
			}
			else {
				buff.setOptionalJMSSqlSchemaObjImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJMySqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalJMySqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJMySqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalJMySqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJMySqlSchemaObjImport( null );
			}
			else {
				buff.setOptionalJMySqlSchemaObjImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJOracleSchemaObjMembers( null );
			}
			else {
				buff.setOptionalJOracleSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJOracleSchemaObjImpl( null );
			}
			else {
				buff.setOptionalJOracleSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJOracleSchemaObjImport( null );
			}
			else {
				buff.setOptionalJOracleSchemaObjImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJPgSqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalJPgSqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJPgSqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalJPgSqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJPgSqlSchemaObjImport( null );
			}
			else {
				buff.setOptionalJPgSqlSchemaObjImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJRamSchemaObjMembers( null );
			}
			else {
				buff.setOptionalJRamSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJRamSchemaObjImpl( null );
			}
			else {
				buff.setOptionalJRamSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJRamSchemaObjImport( null );
			}
			else {
				buff.setOptionalJRamSchemaObjImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgSchemaImport( null );
			}
			else {
				buff.setOptionalJXMsgSchemaImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgSchemaFormatters( null );
			}
			else {
				buff.setOptionalJXMsgSchemaFormatters( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgClientSchemaImport( null );
			}
			else {
				buff.setOptionalJXMsgClientSchemaImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgClientSchemaBody( null );
			}
			else {
				buff.setOptionalJXMsgClientSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgRqstSchemaBody( null );
			}
			else {
				buff.setOptionalJXMsgRqstSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgRqstSchemaImport( null );
			}
			else {
				buff.setOptionalJXMsgRqstSchemaImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgRqstSchemaWireParsers( null );
			}
			else {
				buff.setOptionalJXMsgRqstSchemaWireParsers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgRqstSchemaXsdSpec( null );
			}
			else {
				buff.setOptionalJXMsgRqstSchemaXsdSpec( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgRqstSchemaXsdElementList( null );
			}
			else {
				buff.setOptionalJXMsgRqstSchemaXsdElementList( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgRspnSchemaBody( null );
			}
			else {
				buff.setOptionalJXMsgRspnSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgRspnSchemaImport( null );
			}
			else {
				buff.setOptionalJXMsgRspnSchemaImport( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgRspnSchemaWireParsers( null );
			}
			else {
				buff.setOptionalJXMsgRspnSchemaWireParsers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgRspnSchemaXsdElementList( null );
			}
			else {
				buff.setOptionalJXMsgRspnSchemaXsdElementList( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalJXMsgRspnSchemaXsdSpec( null );
			}
			else {
				buff.setOptionalJXMsgRspnSchemaXsdSpec( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppSchemaObjInclude( null );
			}
			else {
				buff.setOptionalCppSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppSchemaObjInterface( null );
			}
			else {
				buff.setOptionalCppSchemaObjInterface( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCppSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppSchemaObjImplementation( null );
			}
			else {
				buff.setOptionalCppSchemaObjImplementation( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppDb2LUWSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCppDb2LUWSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppDb2LUWSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCppDb2LUWSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppDb2LUWSchemaObjInclude( null );
			}
			else {
				buff.setOptionalCppDb2LUWSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppMSSqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCppMSSqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppMSSqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCppMSSqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppMSSqlSchemaObjInclude( null );
			}
			else {
				buff.setOptionalCppMSSqlSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppMySqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCppMySqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppMySqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCppMySqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppMySqlSchemaObjInclude( null );
			}
			else {
				buff.setOptionalCppMySqlSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppOracleSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCppOracleSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppOracleSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCppOracleSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppOracleSchemaObjInclude( null );
			}
			else {
				buff.setOptionalCppOracleSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppPgSqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCppPgSqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppPgSqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCppPgSqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppPgSqlSchemaObjInclude( null );
			}
			else {
				buff.setOptionalCppPgSqlSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppRamSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCppRamSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppRamSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCppRamSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppRamSchemaObjInclude( null );
			}
			else {
				buff.setOptionalCppRamSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgSchemaInclude( null );
			}
			else {
				buff.setOptionalCppXMsgSchemaInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgSchemaFormatters( null );
			}
			else {
				buff.setOptionalCppXMsgSchemaFormatters( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgClientSchemaInclude( null );
			}
			else {
				buff.setOptionalCppXMsgClientSchemaInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgClientSchemaBody( null );
			}
			else {
				buff.setOptionalCppXMsgClientSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgRqstSchemaBody( null );
			}
			else {
				buff.setOptionalCppXMsgRqstSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgRqstSchemaInclude( null );
			}
			else {
				buff.setOptionalCppXMsgRqstSchemaInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgRqstSchemaWireParsers( null );
			}
			else {
				buff.setOptionalCppXMsgRqstSchemaWireParsers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgRqstSchemaXsdSpec( null );
			}
			else {
				buff.setOptionalCppXMsgRqstSchemaXsdSpec( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgRqstSchemaXsdElementList( null );
			}
			else {
				buff.setOptionalCppXMsgRqstSchemaXsdElementList( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgRspnSchemaBody( null );
			}
			else {
				buff.setOptionalCppXMsgRspnSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgRspnSchemaInclude( null );
			}
			else {
				buff.setOptionalCppXMsgRspnSchemaInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgRspnSchemaWireParsers( null );
			}
			else {
				buff.setOptionalCppXMsgRspnSchemaWireParsers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgRspnSchemaXsdElementList( null );
			}
			else {
				buff.setOptionalCppXMsgRspnSchemaXsdElementList( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCppXMsgRspnSchemaXsdSpec( null );
			}
			else {
				buff.setOptionalCppXMsgRspnSchemaXsdSpec( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppSchemaObjInclude( null );
			}
			else {
				buff.setOptionalHppSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppSchemaObjInterface( null );
			}
			else {
				buff.setOptionalHppSchemaObjInterface( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppSchemaObjMembers( null );
			}
			else {
				buff.setOptionalHppSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppSchemaObjImplementation( null );
			}
			else {
				buff.setOptionalHppSchemaObjImplementation( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppDb2LUWSchemaObjMembers( null );
			}
			else {
				buff.setOptionalHppDb2LUWSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppDb2LUWSchemaObjImpl( null );
			}
			else {
				buff.setOptionalHppDb2LUWSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppDb2LUWSchemaObjInclude( null );
			}
			else {
				buff.setOptionalHppDb2LUWSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppMSSqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalHppMSSqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppMSSqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalHppMSSqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppMSSqlSchemaObjInclude( null );
			}
			else {
				buff.setOptionalHppMSSqlSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppMySqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalHppMySqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppMySqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalHppMySqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppMySqlSchemaObjInclude( null );
			}
			else {
				buff.setOptionalHppMySqlSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppOracleSchemaObjMembers( null );
			}
			else {
				buff.setOptionalHppOracleSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppOracleSchemaObjImpl( null );
			}
			else {
				buff.setOptionalHppOracleSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppOracleSchemaObjInclude( null );
			}
			else {
				buff.setOptionalHppOracleSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppPgSqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalHppPgSqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppPgSqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalHppPgSqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppPgSqlSchemaObjInclude( null );
			}
			else {
				buff.setOptionalHppPgSqlSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppRamSchemaObjMembers( null );
			}
			else {
				buff.setOptionalHppRamSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppRamSchemaObjImpl( null );
			}
			else {
				buff.setOptionalHppRamSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppRamSchemaObjInclude( null );
			}
			else {
				buff.setOptionalHppRamSchemaObjInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgSchemaInclude( null );
			}
			else {
				buff.setOptionalHppXMsgSchemaInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgSchemaFormatters( null );
			}
			else {
				buff.setOptionalHppXMsgSchemaFormatters( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgClientSchemaInclude( null );
			}
			else {
				buff.setOptionalHppXMsgClientSchemaInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgClientSchemaBody( null );
			}
			else {
				buff.setOptionalHppXMsgClientSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgRqstSchemaBody( null );
			}
			else {
				buff.setOptionalHppXMsgRqstSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgRqstSchemaInclude( null );
			}
			else {
				buff.setOptionalHppXMsgRqstSchemaInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgRqstSchemaWireParsers( null );
			}
			else {
				buff.setOptionalHppXMsgRqstSchemaWireParsers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgRqstSchemaXsdSpec( null );
			}
			else {
				buff.setOptionalHppXMsgRqstSchemaXsdSpec( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgRqstSchemaXsdElementList( null );
			}
			else {
				buff.setOptionalHppXMsgRqstSchemaXsdElementList( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgRspnSchemaBody( null );
			}
			else {
				buff.setOptionalHppXMsgRspnSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgRspnSchemaInclude( null );
			}
			else {
				buff.setOptionalHppXMsgRspnSchemaInclude( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgRspnSchemaWireParsers( null );
			}
			else {
				buff.setOptionalHppXMsgRspnSchemaWireParsers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgRspnSchemaXsdElementList( null );
			}
			else {
				buff.setOptionalHppXMsgRspnSchemaXsdElementList( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalHppXMsgRspnSchemaXsdSpec( null );
			}
			else {
				buff.setOptionalHppXMsgRspnSchemaXsdSpec( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsSchemaObjUsing( null );
			}
			else {
				buff.setOptionalCsSchemaObjUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsSchemaObjInterface( null );
			}
			else {
				buff.setOptionalCsSchemaObjInterface( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCsSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsSchemaObjImplementation( null );
			}
			else {
				buff.setOptionalCsSchemaObjImplementation( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsDb2LUWSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCsDb2LUWSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsDb2LUWSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCsDb2LUWSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsDb2LUWSchemaObjUsing( null );
			}
			else {
				buff.setOptionalCsDb2LUWSchemaObjUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsMSSqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCsMSSqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsMSSqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCsMSSqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsMSSqlSchemaObjUsing( null );
			}
			else {
				buff.setOptionalCsMSSqlSchemaObjUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsMySqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCsMySqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsMySqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCsMySqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsMySqlSchemaObjUsing( null );
			}
			else {
				buff.setOptionalCsMySqlSchemaObjUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsOracleSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCsOracleSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsOracleSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCsOracleSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsOracleSchemaObjUsing( null );
			}
			else {
				buff.setOptionalCsOracleSchemaObjUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsPgSqlSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCsPgSqlSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsPgSqlSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCsPgSqlSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsPgSqlSchemaObjUsing( null );
			}
			else {
				buff.setOptionalCsPgSqlSchemaObjUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsRamSchemaObjMembers( null );
			}
			else {
				buff.setOptionalCsRamSchemaObjMembers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsRamSchemaObjImpl( null );
			}
			else {
				buff.setOptionalCsRamSchemaObjImpl( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsRamSchemaObjUsing( null );
			}
			else {
				buff.setOptionalCsRamSchemaObjUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgSchemaUsing( null );
			}
			else {
				buff.setOptionalCsXMsgSchemaUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgSchemaFormatters( null );
			}
			else {
				buff.setOptionalCsXMsgSchemaFormatters( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgClientSchemaUsing( null );
			}
			else {
				buff.setOptionalCsXMsgClientSchemaUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgClientSchemaBody( null );
			}
			else {
				buff.setOptionalCsXMsgClientSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgRqstSchemaBody( null );
			}
			else {
				buff.setOptionalCsXMsgRqstSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgRqstSchemaUsing( null );
			}
			else {
				buff.setOptionalCsXMsgRqstSchemaUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgRqstSchemaWireParsers( null );
			}
			else {
				buff.setOptionalCsXMsgRqstSchemaWireParsers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgRqstSchemaXsdSpec( null );
			}
			else {
				buff.setOptionalCsXMsgRqstSchemaXsdSpec( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgRqstSchemaXsdElementList( null );
			}
			else {
				buff.setOptionalCsXMsgRqstSchemaXsdElementList( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgRspnSchemaBody( null );
			}
			else {
				buff.setOptionalCsXMsgRspnSchemaBody( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgRspnSchemaUsing( null );
			}
			else {
				buff.setOptionalCsXMsgRspnSchemaUsing( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgRspnSchemaWireParsers( null );
			}
			else {
				buff.setOptionalCsXMsgRspnSchemaWireParsers( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgRspnSchemaXsdElementList( null );
			}
			else {
				buff.setOptionalCsXMsgRspnSchemaXsdElementList( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalCsXMsgRspnSchemaXsdSpec( null );
			}
			else {
				buff.setOptionalCsXMsgRspnSchemaXsdSpec( colVal );
			}
		}
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFBamSchemaDefBuff readDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamSchemaDefBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamSchemaDefBuff lockDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamSchemaDefBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamSchemaDefBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFBamSchemaDefBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFBamSchemaDefBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlSchemaDefTable.readDerivedByIdIdx() ";
		CFBamSchemaDefBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		return( buff );
	}

	public CFBamSchemaDefBuff[] readDerivedByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByTenantIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamSchemaDefBuff[] buffList = readBuffByTenantIdx( Authorization,
				TenantId );
		return( buffList );

	}

	public CFBamSchemaDefBuff[] readDerivedByCTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByCTenantIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamSchemaDefBuff[] buffList = readBuffByCTenantIdx( Authorization,
				TenantId );
		return( buffList );

	}

	public CFBamSchemaDefBuff[] readDerivedByMinorVersionIdx( CFSecAuthorization Authorization,
		long TenantId,
		long MinorVersionId )
	{
		final String S_ProcName = "readDerivedByMinorVersionIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamSchemaDefBuff[] buffList = readBuffByMinorVersionIdx( Authorization,
				TenantId,
				MinorVersionId );
		return( buffList );

	}

	public CFBamSchemaDefBuff readDerivedByUNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long MinorVersionId,
		String Name )
	{
		final String S_ProcName = "CFBamPgSqlSchemaDefTable.readDerivedByUNameIdx() ";
		CFBamSchemaDefBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUNameIdx( Authorization,
				TenantId,
				MinorVersionId,
				Name );
		return( buff );
	}

	public CFBamSchemaDefBuff[] readDerivedByAuthEMailIdx( CFSecAuthorization Authorization,
		long TenantId,
		String AuthorEMail )
	{
		final String S_ProcName = "readDerivedByAuthEMailIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamSchemaDefBuff[] buffList = readBuffByAuthEMailIdx( Authorization,
				TenantId,
				AuthorEMail );
		return( buffList );

	}

	public CFBamSchemaDefBuff[] readDerivedByProjectURLIdx( CFSecAuthorization Authorization,
		long TenantId,
		String ProjectURL )
	{
		final String S_ProcName = "readDerivedByProjectURLIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamSchemaDefBuff[] buffList = readBuffByProjectURLIdx( Authorization,
				TenantId,
				ProjectURL );
		return( buffList );

	}

	public CFBamSchemaDefBuff readDerivedByPubURIIdx( CFSecAuthorization Authorization,
		long TenantId,
		String PublishURI )
	{
		final String S_ProcName = "CFBamPgSqlSchemaDefTable.readDerivedByPubURIIdx() ";
		CFBamSchemaDefBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByPubURIIdx( Authorization,
				TenantId,
				PublishURI );
		return( buff );
	}

	public CFBamSchemaDefBuff readBuff( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "readBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_schemadef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByPKey == null ) {
				stmtReadBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByPKey.setLong( argIdx++, TenantId );
			stmtReadBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamSchemaDefBuff lockBuff( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "lockBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_schemadef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtLockBuffByPKey == null ) {
				stmtLockBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtLockBuffByPKey.setLong( argIdx++, TenantId );
			stmtLockBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamSchemaDefBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_schemadef_all( ?, ?, ?, ?, ? )";
			if( stmtReadAllBuff == null ) {
				stmtReadAllBuff = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			resultSet = stmtReadAllBuff.executeQuery();
			List<CFBamSchemaDefBuff> buffList = new LinkedList<CFBamSchemaDefBuff>();
			while( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamSchemaDefBuff[] retBuff = new CFBamSchemaDefBuff[ buffList.size() ];
			Iterator<CFBamSchemaDefBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamSchemaDefBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_schemadef_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByIdIdx == null ) {
				stmtReadBuffByIdIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByIdIdx.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamSchemaDefBuff[] readBuffByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_schemadef_by_tenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByTenantIdx == null ) {
				stmtReadBuffByTenantIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByTenantIdx.setLong( argIdx++, TenantId );
			resultSet = stmtReadBuffByTenantIdx.executeQuery();
			List<CFBamSchemaDefBuff> buffList = new LinkedList<CFBamSchemaDefBuff>();
			while( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamSchemaDefBuff[] retBuff = new CFBamSchemaDefBuff[ buffList.size() ];
			Iterator<CFBamSchemaDefBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamSchemaDefBuff[] readBuffByCTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByCTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_schemadef_by_ctenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByCTenantIdx == null ) {
				stmtReadBuffByCTenantIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByCTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByCTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByCTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByCTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByCTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByCTenantIdx.setLong( argIdx++, TenantId );
			resultSet = stmtReadBuffByCTenantIdx.executeQuery();
			List<CFBamSchemaDefBuff> buffList = new LinkedList<CFBamSchemaDefBuff>();
			while( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamSchemaDefBuff[] retBuff = new CFBamSchemaDefBuff[ buffList.size() ];
			Iterator<CFBamSchemaDefBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamSchemaDefBuff[] readBuffByMinorVersionIdx( CFSecAuthorization Authorization,
		long TenantId,
		long MinorVersionId )
	{
		final String S_ProcName = "readBuffByMinorVersionIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_schemadef_by_minorversionidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByMinorVersionIdx == null ) {
				stmtReadBuffByMinorVersionIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByMinorVersionIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByMinorVersionIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByMinorVersionIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByMinorVersionIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByMinorVersionIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByMinorVersionIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByMinorVersionIdx.setLong( argIdx++, MinorVersionId );
			resultSet = stmtReadBuffByMinorVersionIdx.executeQuery();
			List<CFBamSchemaDefBuff> buffList = new LinkedList<CFBamSchemaDefBuff>();
			while( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamSchemaDefBuff[] retBuff = new CFBamSchemaDefBuff[ buffList.size() ];
			Iterator<CFBamSchemaDefBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamSchemaDefBuff readBuffByUNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long MinorVersionId,
		String Name )
	{
		final String S_ProcName = "readBuffByUNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_schemadef_by_unameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUNameIdx == null ) {
				stmtReadBuffByUNameIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByUNameIdx.setLong( argIdx++, MinorVersionId );
			stmtReadBuffByUNameIdx.setString( argIdx++, Name );
			resultSet = stmtReadBuffByUNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamSchemaDefBuff[] readBuffByAuthEMailIdx( CFSecAuthorization Authorization,
		long TenantId,
		String AuthorEMail )
	{
		final String S_ProcName = "readBuffByAuthEMailIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_schemadef_by_authemailidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByAuthEMailIdx == null ) {
				stmtReadBuffByAuthEMailIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByAuthEMailIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByAuthEMailIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByAuthEMailIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByAuthEMailIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByAuthEMailIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByAuthEMailIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByAuthEMailIdx.setString( argIdx++, AuthorEMail );
			resultSet = stmtReadBuffByAuthEMailIdx.executeQuery();
			List<CFBamSchemaDefBuff> buffList = new LinkedList<CFBamSchemaDefBuff>();
			while( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamSchemaDefBuff[] retBuff = new CFBamSchemaDefBuff[ buffList.size() ];
			Iterator<CFBamSchemaDefBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamSchemaDefBuff[] readBuffByProjectURLIdx( CFSecAuthorization Authorization,
		long TenantId,
		String ProjectURL )
	{
		final String S_ProcName = "readBuffByProjectURLIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_schemadef_by_projecturlidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByProjectURLIdx == null ) {
				stmtReadBuffByProjectURLIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByProjectURLIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByProjectURLIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByProjectURLIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByProjectURLIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByProjectURLIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByProjectURLIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByProjectURLIdx.setString( argIdx++, ProjectURL );
			resultSet = stmtReadBuffByProjectURLIdx.executeQuery();
			List<CFBamSchemaDefBuff> buffList = new LinkedList<CFBamSchemaDefBuff>();
			while( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamSchemaDefBuff[] retBuff = new CFBamSchemaDefBuff[ buffList.size() ];
			Iterator<CFBamSchemaDefBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamSchemaDefBuff readBuffByPubURIIdx( CFSecAuthorization Authorization,
		long TenantId,
		String PublishURI )
	{
		final String S_ProcName = "readBuffByPubURIIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_schemadef_by_puburiidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByPubURIIdx == null ) {
				stmtReadBuffByPubURIIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByPubURIIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPubURIIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByPubURIIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByPubURIIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPubURIIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByPubURIIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByPubURIIdx.setString( argIdx++, PublishURI );
			resultSet = stmtReadBuffByPubURIIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamSchemaDefBuff buff = unpackSchemaDefResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void updateSchemaDef( CFSecAuthorization Authorization,
		CFBamSchemaDefBuff Buff )
	{
		final String S_ProcName = "updateSchemaDef";
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();
			long MinorVersionId = Buff.getRequiredMinorVersionId();
			String Name = Buff.getRequiredName();
			String DbName = Buff.getOptionalDbName();
			String ShortName = Buff.getOptionalShortName();
			String Label = Buff.getOptionalLabel();
			String ShortDescription = Buff.getOptionalShortDescription();
			String Description = Buff.getOptionalDescription();
			String PublicLicenseName = Buff.getRequiredPublicLicenseName();
			String PublicLicenseText = Buff.getRequiredPublicLicenseText();
			String ImplementationLicenseName = Buff.getOptionalImplementationLicenseName();
			String ImplementationLicenseText = Buff.getOptionalImplementationLicenseText();
			String CopyrightPeriod = Buff.getRequiredCopyrightPeriod();
			String CopyrightHolder = Buff.getRequiredCopyrightHolder();
			String AuthorEMail = Buff.getRequiredAuthorEMail();
			String ProjectURL = Buff.getRequiredProjectURL();
			String PublishURI = Buff.getRequiredPublishURI();
			String JSchemaObjImport = Buff.getOptionalJSchemaObjImport();
			String JSchemaObjInterface = Buff.getOptionalJSchemaObjInterface();
			String JSchemaObjMembers = Buff.getOptionalJSchemaObjMembers();
			String JSchemaObjImplementation = Buff.getOptionalJSchemaObjImplementation();
			String JDb2LUWSchemaObjMembers = Buff.getOptionalJDb2LUWSchemaObjMembers();
			String JDb2LUWSchemaObjImpl = Buff.getOptionalJDb2LUWSchemaObjImpl();
			String JDb2LUWSchemaObjImport = Buff.getOptionalJDb2LUWSchemaObjImport();
			String JMSSqlSchemaObjMembers = Buff.getOptionalJMSSqlSchemaObjMembers();
			String JMSSqlSchemaObjImpl = Buff.getOptionalJMSSqlSchemaObjImpl();
			String JMSSqlSchemaObjImport = Buff.getOptionalJMSSqlSchemaObjImport();
			String JMySqlSchemaObjMembers = Buff.getOptionalJMySqlSchemaObjMembers();
			String JMySqlSchemaObjImpl = Buff.getOptionalJMySqlSchemaObjImpl();
			String JMySqlSchemaObjImport = Buff.getOptionalJMySqlSchemaObjImport();
			String JOracleSchemaObjMembers = Buff.getOptionalJOracleSchemaObjMembers();
			String JOracleSchemaObjImpl = Buff.getOptionalJOracleSchemaObjImpl();
			String JOracleSchemaObjImport = Buff.getOptionalJOracleSchemaObjImport();
			String JPgSqlSchemaObjMembers = Buff.getOptionalJPgSqlSchemaObjMembers();
			String JPgSqlSchemaObjImpl = Buff.getOptionalJPgSqlSchemaObjImpl();
			String JPgSqlSchemaObjImport = Buff.getOptionalJPgSqlSchemaObjImport();
			String JRamSchemaObjMembers = Buff.getOptionalJRamSchemaObjMembers();
			String JRamSchemaObjImpl = Buff.getOptionalJRamSchemaObjImpl();
			String JRamSchemaObjImport = Buff.getOptionalJRamSchemaObjImport();
			String JXMsgSchemaImport = Buff.getOptionalJXMsgSchemaImport();
			String JXMsgSchemaFormatters = Buff.getOptionalJXMsgSchemaFormatters();
			String JXMsgClientSchemaImport = Buff.getOptionalJXMsgClientSchemaImport();
			String JXMsgClientSchemaBody = Buff.getOptionalJXMsgClientSchemaBody();
			String JXMsgRqstSchemaBody = Buff.getOptionalJXMsgRqstSchemaBody();
			String JXMsgRqstSchemaImport = Buff.getOptionalJXMsgRqstSchemaImport();
			String JXMsgRqstSchemaWireParsers = Buff.getOptionalJXMsgRqstSchemaWireParsers();
			String JXMsgRqstSchemaXsdSpec = Buff.getOptionalJXMsgRqstSchemaXsdSpec();
			String JXMsgRqstSchemaXsdElementList = Buff.getOptionalJXMsgRqstSchemaXsdElementList();
			String JXMsgRspnSchemaBody = Buff.getOptionalJXMsgRspnSchemaBody();
			String JXMsgRspnSchemaImport = Buff.getOptionalJXMsgRspnSchemaImport();
			String JXMsgRspnSchemaWireParsers = Buff.getOptionalJXMsgRspnSchemaWireParsers();
			String JXMsgRspnSchemaXsdElementList = Buff.getOptionalJXMsgRspnSchemaXsdElementList();
			String JXMsgRspnSchemaXsdSpec = Buff.getOptionalJXMsgRspnSchemaXsdSpec();
			String CppSchemaObjInclude = Buff.getOptionalCppSchemaObjInclude();
			String CppSchemaObjInterface = Buff.getOptionalCppSchemaObjInterface();
			String CppSchemaObjMembers = Buff.getOptionalCppSchemaObjMembers();
			String CppSchemaObjImplementation = Buff.getOptionalCppSchemaObjImplementation();
			String CppDb2LUWSchemaObjMembers = Buff.getOptionalCppDb2LUWSchemaObjMembers();
			String CppDb2LUWSchemaObjImpl = Buff.getOptionalCppDb2LUWSchemaObjImpl();
			String CppDb2LUWSchemaObjInclude = Buff.getOptionalCppDb2LUWSchemaObjInclude();
			String CppMSSqlSchemaObjMembers = Buff.getOptionalCppMSSqlSchemaObjMembers();
			String CppMSSqlSchemaObjImpl = Buff.getOptionalCppMSSqlSchemaObjImpl();
			String CppMSSqlSchemaObjInclude = Buff.getOptionalCppMSSqlSchemaObjInclude();
			String CppMySqlSchemaObjMembers = Buff.getOptionalCppMySqlSchemaObjMembers();
			String CppMySqlSchemaObjImpl = Buff.getOptionalCppMySqlSchemaObjImpl();
			String CppMySqlSchemaObjInclude = Buff.getOptionalCppMySqlSchemaObjInclude();
			String CppOracleSchemaObjMembers = Buff.getOptionalCppOracleSchemaObjMembers();
			String CppOracleSchemaObjImpl = Buff.getOptionalCppOracleSchemaObjImpl();
			String CppOracleSchemaObjInclude = Buff.getOptionalCppOracleSchemaObjInclude();
			String CppPgSqlSchemaObjMembers = Buff.getOptionalCppPgSqlSchemaObjMembers();
			String CppPgSqlSchemaObjImpl = Buff.getOptionalCppPgSqlSchemaObjImpl();
			String CppPgSqlSchemaObjInclude = Buff.getOptionalCppPgSqlSchemaObjInclude();
			String CppRamSchemaObjMembers = Buff.getOptionalCppRamSchemaObjMembers();
			String CppRamSchemaObjImpl = Buff.getOptionalCppRamSchemaObjImpl();
			String CppRamSchemaObjInclude = Buff.getOptionalCppRamSchemaObjInclude();
			String CppXMsgSchemaInclude = Buff.getOptionalCppXMsgSchemaInclude();
			String CppXMsgSchemaFormatters = Buff.getOptionalCppXMsgSchemaFormatters();
			String CppXMsgClientSchemaInclude = Buff.getOptionalCppXMsgClientSchemaInclude();
			String CppXMsgClientSchemaBody = Buff.getOptionalCppXMsgClientSchemaBody();
			String CppXMsgRqstSchemaBody = Buff.getOptionalCppXMsgRqstSchemaBody();
			String CppXMsgRqstSchemaInclude = Buff.getOptionalCppXMsgRqstSchemaInclude();
			String CppXMsgRqstSchemaWireParsers = Buff.getOptionalCppXMsgRqstSchemaWireParsers();
			String CppXMsgRqstSchemaXsdSpec = Buff.getOptionalCppXMsgRqstSchemaXsdSpec();
			String CppXMsgRqstSchemaXsdElementList = Buff.getOptionalCppXMsgRqstSchemaXsdElementList();
			String CppXMsgRspnSchemaBody = Buff.getOptionalCppXMsgRspnSchemaBody();
			String CppXMsgRspnSchemaInclude = Buff.getOptionalCppXMsgRspnSchemaInclude();
			String CppXMsgRspnSchemaWireParsers = Buff.getOptionalCppXMsgRspnSchemaWireParsers();
			String CppXMsgRspnSchemaXsdElementList = Buff.getOptionalCppXMsgRspnSchemaXsdElementList();
			String CppXMsgRspnSchemaXsdSpec = Buff.getOptionalCppXMsgRspnSchemaXsdSpec();
			String HppSchemaObjInclude = Buff.getOptionalHppSchemaObjInclude();
			String HppSchemaObjInterface = Buff.getOptionalHppSchemaObjInterface();
			String HppSchemaObjMembers = Buff.getOptionalHppSchemaObjMembers();
			String HppSchemaObjImplementation = Buff.getOptionalHppSchemaObjImplementation();
			String HppDb2LUWSchemaObjMembers = Buff.getOptionalHppDb2LUWSchemaObjMembers();
			String HppDb2LUWSchemaObjImpl = Buff.getOptionalHppDb2LUWSchemaObjImpl();
			String HppDb2LUWSchemaObjInclude = Buff.getOptionalHppDb2LUWSchemaObjInclude();
			String HppMSSqlSchemaObjMembers = Buff.getOptionalHppMSSqlSchemaObjMembers();
			String HppMSSqlSchemaObjImpl = Buff.getOptionalHppMSSqlSchemaObjImpl();
			String HppMSSqlSchemaObjInclude = Buff.getOptionalHppMSSqlSchemaObjInclude();
			String HppMySqlSchemaObjMembers = Buff.getOptionalHppMySqlSchemaObjMembers();
			String HppMySqlSchemaObjImpl = Buff.getOptionalHppMySqlSchemaObjImpl();
			String HppMySqlSchemaObjInclude = Buff.getOptionalHppMySqlSchemaObjInclude();
			String HppOracleSchemaObjMembers = Buff.getOptionalHppOracleSchemaObjMembers();
			String HppOracleSchemaObjImpl = Buff.getOptionalHppOracleSchemaObjImpl();
			String HppOracleSchemaObjInclude = Buff.getOptionalHppOracleSchemaObjInclude();
			String HppPgSqlSchemaObjMembers = Buff.getOptionalHppPgSqlSchemaObjMembers();
			String HppPgSqlSchemaObjImpl = Buff.getOptionalHppPgSqlSchemaObjImpl();
			String HppPgSqlSchemaObjInclude = Buff.getOptionalHppPgSqlSchemaObjInclude();
			String HppRamSchemaObjMembers = Buff.getOptionalHppRamSchemaObjMembers();
			String HppRamSchemaObjImpl = Buff.getOptionalHppRamSchemaObjImpl();
			String HppRamSchemaObjInclude = Buff.getOptionalHppRamSchemaObjInclude();
			String HppXMsgSchemaInclude = Buff.getOptionalHppXMsgSchemaInclude();
			String HppXMsgSchemaFormatters = Buff.getOptionalHppXMsgSchemaFormatters();
			String HppXMsgClientSchemaInclude = Buff.getOptionalHppXMsgClientSchemaInclude();
			String HppXMsgClientSchemaBody = Buff.getOptionalHppXMsgClientSchemaBody();
			String HppXMsgRqstSchemaBody = Buff.getOptionalHppXMsgRqstSchemaBody();
			String HppXMsgRqstSchemaInclude = Buff.getOptionalHppXMsgRqstSchemaInclude();
			String HppXMsgRqstSchemaWireParsers = Buff.getOptionalHppXMsgRqstSchemaWireParsers();
			String HppXMsgRqstSchemaXsdSpec = Buff.getOptionalHppXMsgRqstSchemaXsdSpec();
			String HppXMsgRqstSchemaXsdElementList = Buff.getOptionalHppXMsgRqstSchemaXsdElementList();
			String HppXMsgRspnSchemaBody = Buff.getOptionalHppXMsgRspnSchemaBody();
			String HppXMsgRspnSchemaInclude = Buff.getOptionalHppXMsgRspnSchemaInclude();
			String HppXMsgRspnSchemaWireParsers = Buff.getOptionalHppXMsgRspnSchemaWireParsers();
			String HppXMsgRspnSchemaXsdElementList = Buff.getOptionalHppXMsgRspnSchemaXsdElementList();
			String HppXMsgRspnSchemaXsdSpec = Buff.getOptionalHppXMsgRspnSchemaXsdSpec();
			String CsSchemaObjUsing = Buff.getOptionalCsSchemaObjUsing();
			String CsSchemaObjInterface = Buff.getOptionalCsSchemaObjInterface();
			String CsSchemaObjMembers = Buff.getOptionalCsSchemaObjMembers();
			String CsSchemaObjImplementation = Buff.getOptionalCsSchemaObjImplementation();
			String CsDb2LUWSchemaObjMembers = Buff.getOptionalCsDb2LUWSchemaObjMembers();
			String CsDb2LUWSchemaObjImpl = Buff.getOptionalCsDb2LUWSchemaObjImpl();
			String CsDb2LUWSchemaObjUsing = Buff.getOptionalCsDb2LUWSchemaObjUsing();
			String CsMSSqlSchemaObjMembers = Buff.getOptionalCsMSSqlSchemaObjMembers();
			String CsMSSqlSchemaObjImpl = Buff.getOptionalCsMSSqlSchemaObjImpl();
			String CsMSSqlSchemaObjUsing = Buff.getOptionalCsMSSqlSchemaObjUsing();
			String CsMySqlSchemaObjMembers = Buff.getOptionalCsMySqlSchemaObjMembers();
			String CsMySqlSchemaObjImpl = Buff.getOptionalCsMySqlSchemaObjImpl();
			String CsMySqlSchemaObjUsing = Buff.getOptionalCsMySqlSchemaObjUsing();
			String CsOracleSchemaObjMembers = Buff.getOptionalCsOracleSchemaObjMembers();
			String CsOracleSchemaObjImpl = Buff.getOptionalCsOracleSchemaObjImpl();
			String CsOracleSchemaObjUsing = Buff.getOptionalCsOracleSchemaObjUsing();
			String CsPgSqlSchemaObjMembers = Buff.getOptionalCsPgSqlSchemaObjMembers();
			String CsPgSqlSchemaObjImpl = Buff.getOptionalCsPgSqlSchemaObjImpl();
			String CsPgSqlSchemaObjUsing = Buff.getOptionalCsPgSqlSchemaObjUsing();
			String CsRamSchemaObjMembers = Buff.getOptionalCsRamSchemaObjMembers();
			String CsRamSchemaObjImpl = Buff.getOptionalCsRamSchemaObjImpl();
			String CsRamSchemaObjUsing = Buff.getOptionalCsRamSchemaObjUsing();
			String CsXMsgSchemaUsing = Buff.getOptionalCsXMsgSchemaUsing();
			String CsXMsgSchemaFormatters = Buff.getOptionalCsXMsgSchemaFormatters();
			String CsXMsgClientSchemaUsing = Buff.getOptionalCsXMsgClientSchemaUsing();
			String CsXMsgClientSchemaBody = Buff.getOptionalCsXMsgClientSchemaBody();
			String CsXMsgRqstSchemaBody = Buff.getOptionalCsXMsgRqstSchemaBody();
			String CsXMsgRqstSchemaUsing = Buff.getOptionalCsXMsgRqstSchemaUsing();
			String CsXMsgRqstSchemaWireParsers = Buff.getOptionalCsXMsgRqstSchemaWireParsers();
			String CsXMsgRqstSchemaXsdSpec = Buff.getOptionalCsXMsgRqstSchemaXsdSpec();
			String CsXMsgRqstSchemaXsdElementList = Buff.getOptionalCsXMsgRqstSchemaXsdElementList();
			String CsXMsgRspnSchemaBody = Buff.getOptionalCsXMsgRspnSchemaBody();
			String CsXMsgRspnSchemaUsing = Buff.getOptionalCsXMsgRspnSchemaUsing();
			String CsXMsgRspnSchemaWireParsers = Buff.getOptionalCsXMsgRspnSchemaWireParsers();
			String CsXMsgRspnSchemaXsdElementList = Buff.getOptionalCsXMsgRspnSchemaXsdElementList();
			String CsXMsgRspnSchemaXsdSpec = Buff.getOptionalCsXMsgRspnSchemaXsdSpec();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_schemadef( ?, ?, ?, ?, ?, ?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"cast( ? as text )" + ", "
						+	"?" + ", "
						+	"cast( ? as text )" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"?" + " )";
			if( stmtUpdateByPKey == null ) {
				stmtUpdateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtUpdateByPKey.setString( argIdx++, ClassCode );
			stmtUpdateByPKey.setLong( argIdx++, TenantId );
			stmtUpdateByPKey.setLong( argIdx++, Id );
			stmtUpdateByPKey.setLong( argIdx++, MinorVersionId );
			stmtUpdateByPKey.setString( argIdx++, Name );
			if( DbName != null ) {
				stmtUpdateByPKey.setString( argIdx++, DbName );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( ShortName != null ) {
				stmtUpdateByPKey.setString( argIdx++, ShortName );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Label != null ) {
				stmtUpdateByPKey.setString( argIdx++, Label );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( ShortDescription != null ) {
				stmtUpdateByPKey.setString( argIdx++, ShortDescription );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Description != null ) {
				stmtUpdateByPKey.setString( argIdx++, Description );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtUpdateByPKey.setString( argIdx++, PublicLicenseName );
			stmtUpdateByPKey.setString( argIdx++, PublicLicenseText );
			if( ImplementationLicenseName != null ) {
				stmtUpdateByPKey.setString( argIdx++, ImplementationLicenseName );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( ImplementationLicenseText != null ) {
				stmtUpdateByPKey.setString( argIdx++, ImplementationLicenseText );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtUpdateByPKey.setString( argIdx++, CopyrightPeriod );
			stmtUpdateByPKey.setString( argIdx++, CopyrightHolder );
			stmtUpdateByPKey.setString( argIdx++, AuthorEMail );
			stmtUpdateByPKey.setString( argIdx++, ProjectURL );
			stmtUpdateByPKey.setString( argIdx++, PublishURI );
			if( JSchemaObjImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JSchemaObjImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JSchemaObjInterface != null ) {
				stmtUpdateByPKey.setString( argIdx++, JSchemaObjInterface );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, JSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JSchemaObjImplementation != null ) {
				stmtUpdateByPKey.setString( argIdx++, JSchemaObjImplementation );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JDb2LUWSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, JDb2LUWSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JDb2LUWSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, JDb2LUWSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JDb2LUWSchemaObjImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JDb2LUWSchemaObjImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMSSqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, JMSSqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMSSqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, JMSSqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMSSqlSchemaObjImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JMSSqlSchemaObjImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMySqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, JMySqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMySqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, JMySqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JMySqlSchemaObjImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JMySqlSchemaObjImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JOracleSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, JOracleSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JOracleSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, JOracleSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JOracleSchemaObjImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JOracleSchemaObjImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JPgSqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, JPgSqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JPgSqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, JPgSqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JPgSqlSchemaObjImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JPgSqlSchemaObjImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JRamSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, JRamSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JRamSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, JRamSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JRamSchemaObjImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JRamSchemaObjImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgSchemaImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgSchemaImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgSchemaFormatters != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgSchemaFormatters );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgClientSchemaImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgClientSchemaImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgClientSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgClientSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRqstSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgRqstSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRqstSchemaImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgRqstSchemaImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRqstSchemaWireParsers != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgRqstSchemaWireParsers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRqstSchemaXsdSpec != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgRqstSchemaXsdSpec );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRqstSchemaXsdElementList != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgRqstSchemaXsdElementList );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRspnSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgRspnSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRspnSchemaImport != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgRspnSchemaImport );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRspnSchemaWireParsers != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgRspnSchemaWireParsers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRspnSchemaXsdElementList != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgRspnSchemaXsdElementList );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( JXMsgRspnSchemaXsdSpec != null ) {
				stmtUpdateByPKey.setString( argIdx++, JXMsgRspnSchemaXsdSpec );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppSchemaObjInterface != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppSchemaObjInterface );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppSchemaObjImplementation != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppSchemaObjImplementation );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppDb2LUWSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppDb2LUWSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppDb2LUWSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppDb2LUWSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppDb2LUWSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppDb2LUWSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMSSqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppMSSqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMSSqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppMSSqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMSSqlSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppMSSqlSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMySqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppMySqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMySqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppMySqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppMySqlSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppMySqlSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppOracleSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppOracleSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppOracleSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppOracleSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppOracleSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppOracleSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppPgSqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppPgSqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppPgSqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppPgSqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppPgSqlSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppPgSqlSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppRamSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppRamSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppRamSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppRamSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppRamSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppRamSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgSchemaInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgSchemaInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgSchemaFormatters != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgSchemaFormatters );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgClientSchemaInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgClientSchemaInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgClientSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgClientSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRqstSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgRqstSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRqstSchemaInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgRqstSchemaInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRqstSchemaWireParsers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgRqstSchemaWireParsers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRqstSchemaXsdSpec != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgRqstSchemaXsdSpec );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRqstSchemaXsdElementList != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgRqstSchemaXsdElementList );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRspnSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgRspnSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRspnSchemaInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgRspnSchemaInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRspnSchemaWireParsers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgRspnSchemaWireParsers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRspnSchemaXsdElementList != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgRspnSchemaXsdElementList );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CppXMsgRspnSchemaXsdSpec != null ) {
				stmtUpdateByPKey.setString( argIdx++, CppXMsgRspnSchemaXsdSpec );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppSchemaObjInterface != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppSchemaObjInterface );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppSchemaObjImplementation != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppSchemaObjImplementation );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppDb2LUWSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppDb2LUWSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppDb2LUWSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppDb2LUWSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppDb2LUWSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppDb2LUWSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMSSqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppMSSqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMSSqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppMSSqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMSSqlSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppMSSqlSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMySqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppMySqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMySqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppMySqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppMySqlSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppMySqlSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppOracleSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppOracleSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppOracleSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppOracleSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppOracleSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppOracleSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppPgSqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppPgSqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppPgSqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppPgSqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppPgSqlSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppPgSqlSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppRamSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppRamSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppRamSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppRamSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppRamSchemaObjInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppRamSchemaObjInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgSchemaInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgSchemaInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgSchemaFormatters != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgSchemaFormatters );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgClientSchemaInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgClientSchemaInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgClientSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgClientSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRqstSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgRqstSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRqstSchemaInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgRqstSchemaInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRqstSchemaWireParsers != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgRqstSchemaWireParsers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRqstSchemaXsdSpec != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgRqstSchemaXsdSpec );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRqstSchemaXsdElementList != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgRqstSchemaXsdElementList );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRspnSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgRspnSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRspnSchemaInclude != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgRspnSchemaInclude );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRspnSchemaWireParsers != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgRspnSchemaWireParsers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRspnSchemaXsdElementList != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgRspnSchemaXsdElementList );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( HppXMsgRspnSchemaXsdSpec != null ) {
				stmtUpdateByPKey.setString( argIdx++, HppXMsgRspnSchemaXsdSpec );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsSchemaObjUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsSchemaObjUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsSchemaObjInterface != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsSchemaObjInterface );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsSchemaObjImplementation != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsSchemaObjImplementation );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsDb2LUWSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsDb2LUWSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsDb2LUWSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsDb2LUWSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsDb2LUWSchemaObjUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsDb2LUWSchemaObjUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMSSqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsMSSqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMSSqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsMSSqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMSSqlSchemaObjUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsMSSqlSchemaObjUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMySqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsMySqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMySqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsMySqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsMySqlSchemaObjUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsMySqlSchemaObjUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsOracleSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsOracleSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsOracleSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsOracleSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsOracleSchemaObjUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsOracleSchemaObjUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsPgSqlSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsPgSqlSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsPgSqlSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsPgSqlSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsPgSqlSchemaObjUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsPgSqlSchemaObjUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsRamSchemaObjMembers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsRamSchemaObjMembers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsRamSchemaObjImpl != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsRamSchemaObjImpl );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsRamSchemaObjUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsRamSchemaObjUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgSchemaUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgSchemaUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgSchemaFormatters != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgSchemaFormatters );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgClientSchemaUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgClientSchemaUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgClientSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgClientSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRqstSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgRqstSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRqstSchemaUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgRqstSchemaUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRqstSchemaWireParsers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgRqstSchemaWireParsers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRqstSchemaXsdSpec != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgRqstSchemaXsdSpec );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRqstSchemaXsdElementList != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgRqstSchemaXsdElementList );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRspnSchemaBody != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgRspnSchemaBody );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRspnSchemaUsing != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgRspnSchemaUsing );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRspnSchemaWireParsers != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgRspnSchemaWireParsers );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRspnSchemaXsdElementList != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgRspnSchemaXsdElementList );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( CsXMsgRspnSchemaXsdSpec != null ) {
				stmtUpdateByPKey.setString( argIdx++, CsXMsgRspnSchemaXsdSpec );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamSchemaDefBuff updatedBuff = unpackSchemaDefResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredMinorVersionId( updatedBuff.getRequiredMinorVersionId() );
				Buff.setRequiredName( updatedBuff.getRequiredName() );
				Buff.setOptionalDbName( updatedBuff.getOptionalDbName() );
				Buff.setOptionalShortName( updatedBuff.getOptionalShortName() );
				Buff.setOptionalLabel( updatedBuff.getOptionalLabel() );
				Buff.setOptionalShortDescription( updatedBuff.getOptionalShortDescription() );
				Buff.setOptionalDescription( updatedBuff.getOptionalDescription() );
				Buff.setRequiredPublicLicenseName( updatedBuff.getRequiredPublicLicenseName() );
				Buff.setRequiredPublicLicenseText( updatedBuff.getRequiredPublicLicenseText() );
				Buff.setOptionalImplementationLicenseName( updatedBuff.getOptionalImplementationLicenseName() );
				Buff.setOptionalImplementationLicenseText( updatedBuff.getOptionalImplementationLicenseText() );
				Buff.setRequiredCopyrightPeriod( updatedBuff.getRequiredCopyrightPeriod() );
				Buff.setRequiredCopyrightHolder( updatedBuff.getRequiredCopyrightHolder() );
				Buff.setRequiredAuthorEMail( updatedBuff.getRequiredAuthorEMail() );
				Buff.setRequiredProjectURL( updatedBuff.getRequiredProjectURL() );
				Buff.setRequiredPublishURI( updatedBuff.getRequiredPublishURI() );
				Buff.setOptionalJSchemaObjImport( updatedBuff.getOptionalJSchemaObjImport() );
				Buff.setOptionalJSchemaObjInterface( updatedBuff.getOptionalJSchemaObjInterface() );
				Buff.setOptionalJSchemaObjMembers( updatedBuff.getOptionalJSchemaObjMembers() );
				Buff.setOptionalJSchemaObjImplementation( updatedBuff.getOptionalJSchemaObjImplementation() );
				Buff.setOptionalJDb2LUWSchemaObjMembers( updatedBuff.getOptionalJDb2LUWSchemaObjMembers() );
				Buff.setOptionalJDb2LUWSchemaObjImpl( updatedBuff.getOptionalJDb2LUWSchemaObjImpl() );
				Buff.setOptionalJDb2LUWSchemaObjImport( updatedBuff.getOptionalJDb2LUWSchemaObjImport() );
				Buff.setOptionalJMSSqlSchemaObjMembers( updatedBuff.getOptionalJMSSqlSchemaObjMembers() );
				Buff.setOptionalJMSSqlSchemaObjImpl( updatedBuff.getOptionalJMSSqlSchemaObjImpl() );
				Buff.setOptionalJMSSqlSchemaObjImport( updatedBuff.getOptionalJMSSqlSchemaObjImport() );
				Buff.setOptionalJMySqlSchemaObjMembers( updatedBuff.getOptionalJMySqlSchemaObjMembers() );
				Buff.setOptionalJMySqlSchemaObjImpl( updatedBuff.getOptionalJMySqlSchemaObjImpl() );
				Buff.setOptionalJMySqlSchemaObjImport( updatedBuff.getOptionalJMySqlSchemaObjImport() );
				Buff.setOptionalJOracleSchemaObjMembers( updatedBuff.getOptionalJOracleSchemaObjMembers() );
				Buff.setOptionalJOracleSchemaObjImpl( updatedBuff.getOptionalJOracleSchemaObjImpl() );
				Buff.setOptionalJOracleSchemaObjImport( updatedBuff.getOptionalJOracleSchemaObjImport() );
				Buff.setOptionalJPgSqlSchemaObjMembers( updatedBuff.getOptionalJPgSqlSchemaObjMembers() );
				Buff.setOptionalJPgSqlSchemaObjImpl( updatedBuff.getOptionalJPgSqlSchemaObjImpl() );
				Buff.setOptionalJPgSqlSchemaObjImport( updatedBuff.getOptionalJPgSqlSchemaObjImport() );
				Buff.setOptionalJRamSchemaObjMembers( updatedBuff.getOptionalJRamSchemaObjMembers() );
				Buff.setOptionalJRamSchemaObjImpl( updatedBuff.getOptionalJRamSchemaObjImpl() );
				Buff.setOptionalJRamSchemaObjImport( updatedBuff.getOptionalJRamSchemaObjImport() );
				Buff.setOptionalJXMsgSchemaImport( updatedBuff.getOptionalJXMsgSchemaImport() );
				Buff.setOptionalJXMsgSchemaFormatters( updatedBuff.getOptionalJXMsgSchemaFormatters() );
				Buff.setOptionalJXMsgClientSchemaImport( updatedBuff.getOptionalJXMsgClientSchemaImport() );
				Buff.setOptionalJXMsgClientSchemaBody( updatedBuff.getOptionalJXMsgClientSchemaBody() );
				Buff.setOptionalJXMsgRqstSchemaBody( updatedBuff.getOptionalJXMsgRqstSchemaBody() );
				Buff.setOptionalJXMsgRqstSchemaImport( updatedBuff.getOptionalJXMsgRqstSchemaImport() );
				Buff.setOptionalJXMsgRqstSchemaWireParsers( updatedBuff.getOptionalJXMsgRqstSchemaWireParsers() );
				Buff.setOptionalJXMsgRqstSchemaXsdSpec( updatedBuff.getOptionalJXMsgRqstSchemaXsdSpec() );
				Buff.setOptionalJXMsgRqstSchemaXsdElementList( updatedBuff.getOptionalJXMsgRqstSchemaXsdElementList() );
				Buff.setOptionalJXMsgRspnSchemaBody( updatedBuff.getOptionalJXMsgRspnSchemaBody() );
				Buff.setOptionalJXMsgRspnSchemaImport( updatedBuff.getOptionalJXMsgRspnSchemaImport() );
				Buff.setOptionalJXMsgRspnSchemaWireParsers( updatedBuff.getOptionalJXMsgRspnSchemaWireParsers() );
				Buff.setOptionalJXMsgRspnSchemaXsdElementList( updatedBuff.getOptionalJXMsgRspnSchemaXsdElementList() );
				Buff.setOptionalJXMsgRspnSchemaXsdSpec( updatedBuff.getOptionalJXMsgRspnSchemaXsdSpec() );
				Buff.setOptionalCppSchemaObjInclude( updatedBuff.getOptionalCppSchemaObjInclude() );
				Buff.setOptionalCppSchemaObjInterface( updatedBuff.getOptionalCppSchemaObjInterface() );
				Buff.setOptionalCppSchemaObjMembers( updatedBuff.getOptionalCppSchemaObjMembers() );
				Buff.setOptionalCppSchemaObjImplementation( updatedBuff.getOptionalCppSchemaObjImplementation() );
				Buff.setOptionalCppDb2LUWSchemaObjMembers( updatedBuff.getOptionalCppDb2LUWSchemaObjMembers() );
				Buff.setOptionalCppDb2LUWSchemaObjImpl( updatedBuff.getOptionalCppDb2LUWSchemaObjImpl() );
				Buff.setOptionalCppDb2LUWSchemaObjInclude( updatedBuff.getOptionalCppDb2LUWSchemaObjInclude() );
				Buff.setOptionalCppMSSqlSchemaObjMembers( updatedBuff.getOptionalCppMSSqlSchemaObjMembers() );
				Buff.setOptionalCppMSSqlSchemaObjImpl( updatedBuff.getOptionalCppMSSqlSchemaObjImpl() );
				Buff.setOptionalCppMSSqlSchemaObjInclude( updatedBuff.getOptionalCppMSSqlSchemaObjInclude() );
				Buff.setOptionalCppMySqlSchemaObjMembers( updatedBuff.getOptionalCppMySqlSchemaObjMembers() );
				Buff.setOptionalCppMySqlSchemaObjImpl( updatedBuff.getOptionalCppMySqlSchemaObjImpl() );
				Buff.setOptionalCppMySqlSchemaObjInclude( updatedBuff.getOptionalCppMySqlSchemaObjInclude() );
				Buff.setOptionalCppOracleSchemaObjMembers( updatedBuff.getOptionalCppOracleSchemaObjMembers() );
				Buff.setOptionalCppOracleSchemaObjImpl( updatedBuff.getOptionalCppOracleSchemaObjImpl() );
				Buff.setOptionalCppOracleSchemaObjInclude( updatedBuff.getOptionalCppOracleSchemaObjInclude() );
				Buff.setOptionalCppPgSqlSchemaObjMembers( updatedBuff.getOptionalCppPgSqlSchemaObjMembers() );
				Buff.setOptionalCppPgSqlSchemaObjImpl( updatedBuff.getOptionalCppPgSqlSchemaObjImpl() );
				Buff.setOptionalCppPgSqlSchemaObjInclude( updatedBuff.getOptionalCppPgSqlSchemaObjInclude() );
				Buff.setOptionalCppRamSchemaObjMembers( updatedBuff.getOptionalCppRamSchemaObjMembers() );
				Buff.setOptionalCppRamSchemaObjImpl( updatedBuff.getOptionalCppRamSchemaObjImpl() );
				Buff.setOptionalCppRamSchemaObjInclude( updatedBuff.getOptionalCppRamSchemaObjInclude() );
				Buff.setOptionalCppXMsgSchemaInclude( updatedBuff.getOptionalCppXMsgSchemaInclude() );
				Buff.setOptionalCppXMsgSchemaFormatters( updatedBuff.getOptionalCppXMsgSchemaFormatters() );
				Buff.setOptionalCppXMsgClientSchemaInclude( updatedBuff.getOptionalCppXMsgClientSchemaInclude() );
				Buff.setOptionalCppXMsgClientSchemaBody( updatedBuff.getOptionalCppXMsgClientSchemaBody() );
				Buff.setOptionalCppXMsgRqstSchemaBody( updatedBuff.getOptionalCppXMsgRqstSchemaBody() );
				Buff.setOptionalCppXMsgRqstSchemaInclude( updatedBuff.getOptionalCppXMsgRqstSchemaInclude() );
				Buff.setOptionalCppXMsgRqstSchemaWireParsers( updatedBuff.getOptionalCppXMsgRqstSchemaWireParsers() );
				Buff.setOptionalCppXMsgRqstSchemaXsdSpec( updatedBuff.getOptionalCppXMsgRqstSchemaXsdSpec() );
				Buff.setOptionalCppXMsgRqstSchemaXsdElementList( updatedBuff.getOptionalCppXMsgRqstSchemaXsdElementList() );
				Buff.setOptionalCppXMsgRspnSchemaBody( updatedBuff.getOptionalCppXMsgRspnSchemaBody() );
				Buff.setOptionalCppXMsgRspnSchemaInclude( updatedBuff.getOptionalCppXMsgRspnSchemaInclude() );
				Buff.setOptionalCppXMsgRspnSchemaWireParsers( updatedBuff.getOptionalCppXMsgRspnSchemaWireParsers() );
				Buff.setOptionalCppXMsgRspnSchemaXsdElementList( updatedBuff.getOptionalCppXMsgRspnSchemaXsdElementList() );
				Buff.setOptionalCppXMsgRspnSchemaXsdSpec( updatedBuff.getOptionalCppXMsgRspnSchemaXsdSpec() );
				Buff.setOptionalHppSchemaObjInclude( updatedBuff.getOptionalHppSchemaObjInclude() );
				Buff.setOptionalHppSchemaObjInterface( updatedBuff.getOptionalHppSchemaObjInterface() );
				Buff.setOptionalHppSchemaObjMembers( updatedBuff.getOptionalHppSchemaObjMembers() );
				Buff.setOptionalHppSchemaObjImplementation( updatedBuff.getOptionalHppSchemaObjImplementation() );
				Buff.setOptionalHppDb2LUWSchemaObjMembers( updatedBuff.getOptionalHppDb2LUWSchemaObjMembers() );
				Buff.setOptionalHppDb2LUWSchemaObjImpl( updatedBuff.getOptionalHppDb2LUWSchemaObjImpl() );
				Buff.setOptionalHppDb2LUWSchemaObjInclude( updatedBuff.getOptionalHppDb2LUWSchemaObjInclude() );
				Buff.setOptionalHppMSSqlSchemaObjMembers( updatedBuff.getOptionalHppMSSqlSchemaObjMembers() );
				Buff.setOptionalHppMSSqlSchemaObjImpl( updatedBuff.getOptionalHppMSSqlSchemaObjImpl() );
				Buff.setOptionalHppMSSqlSchemaObjInclude( updatedBuff.getOptionalHppMSSqlSchemaObjInclude() );
				Buff.setOptionalHppMySqlSchemaObjMembers( updatedBuff.getOptionalHppMySqlSchemaObjMembers() );
				Buff.setOptionalHppMySqlSchemaObjImpl( updatedBuff.getOptionalHppMySqlSchemaObjImpl() );
				Buff.setOptionalHppMySqlSchemaObjInclude( updatedBuff.getOptionalHppMySqlSchemaObjInclude() );
				Buff.setOptionalHppOracleSchemaObjMembers( updatedBuff.getOptionalHppOracleSchemaObjMembers() );
				Buff.setOptionalHppOracleSchemaObjImpl( updatedBuff.getOptionalHppOracleSchemaObjImpl() );
				Buff.setOptionalHppOracleSchemaObjInclude( updatedBuff.getOptionalHppOracleSchemaObjInclude() );
				Buff.setOptionalHppPgSqlSchemaObjMembers( updatedBuff.getOptionalHppPgSqlSchemaObjMembers() );
				Buff.setOptionalHppPgSqlSchemaObjImpl( updatedBuff.getOptionalHppPgSqlSchemaObjImpl() );
				Buff.setOptionalHppPgSqlSchemaObjInclude( updatedBuff.getOptionalHppPgSqlSchemaObjInclude() );
				Buff.setOptionalHppRamSchemaObjMembers( updatedBuff.getOptionalHppRamSchemaObjMembers() );
				Buff.setOptionalHppRamSchemaObjImpl( updatedBuff.getOptionalHppRamSchemaObjImpl() );
				Buff.setOptionalHppRamSchemaObjInclude( updatedBuff.getOptionalHppRamSchemaObjInclude() );
				Buff.setOptionalHppXMsgSchemaInclude( updatedBuff.getOptionalHppXMsgSchemaInclude() );
				Buff.setOptionalHppXMsgSchemaFormatters( updatedBuff.getOptionalHppXMsgSchemaFormatters() );
				Buff.setOptionalHppXMsgClientSchemaInclude( updatedBuff.getOptionalHppXMsgClientSchemaInclude() );
				Buff.setOptionalHppXMsgClientSchemaBody( updatedBuff.getOptionalHppXMsgClientSchemaBody() );
				Buff.setOptionalHppXMsgRqstSchemaBody( updatedBuff.getOptionalHppXMsgRqstSchemaBody() );
				Buff.setOptionalHppXMsgRqstSchemaInclude( updatedBuff.getOptionalHppXMsgRqstSchemaInclude() );
				Buff.setOptionalHppXMsgRqstSchemaWireParsers( updatedBuff.getOptionalHppXMsgRqstSchemaWireParsers() );
				Buff.setOptionalHppXMsgRqstSchemaXsdSpec( updatedBuff.getOptionalHppXMsgRqstSchemaXsdSpec() );
				Buff.setOptionalHppXMsgRqstSchemaXsdElementList( updatedBuff.getOptionalHppXMsgRqstSchemaXsdElementList() );
				Buff.setOptionalHppXMsgRspnSchemaBody( updatedBuff.getOptionalHppXMsgRspnSchemaBody() );
				Buff.setOptionalHppXMsgRspnSchemaInclude( updatedBuff.getOptionalHppXMsgRspnSchemaInclude() );
				Buff.setOptionalHppXMsgRspnSchemaWireParsers( updatedBuff.getOptionalHppXMsgRspnSchemaWireParsers() );
				Buff.setOptionalHppXMsgRspnSchemaXsdElementList( updatedBuff.getOptionalHppXMsgRspnSchemaXsdElementList() );
				Buff.setOptionalHppXMsgRspnSchemaXsdSpec( updatedBuff.getOptionalHppXMsgRspnSchemaXsdSpec() );
				Buff.setOptionalCsSchemaObjUsing( updatedBuff.getOptionalCsSchemaObjUsing() );
				Buff.setOptionalCsSchemaObjInterface( updatedBuff.getOptionalCsSchemaObjInterface() );
				Buff.setOptionalCsSchemaObjMembers( updatedBuff.getOptionalCsSchemaObjMembers() );
				Buff.setOptionalCsSchemaObjImplementation( updatedBuff.getOptionalCsSchemaObjImplementation() );
				Buff.setOptionalCsDb2LUWSchemaObjMembers( updatedBuff.getOptionalCsDb2LUWSchemaObjMembers() );
				Buff.setOptionalCsDb2LUWSchemaObjImpl( updatedBuff.getOptionalCsDb2LUWSchemaObjImpl() );
				Buff.setOptionalCsDb2LUWSchemaObjUsing( updatedBuff.getOptionalCsDb2LUWSchemaObjUsing() );
				Buff.setOptionalCsMSSqlSchemaObjMembers( updatedBuff.getOptionalCsMSSqlSchemaObjMembers() );
				Buff.setOptionalCsMSSqlSchemaObjImpl( updatedBuff.getOptionalCsMSSqlSchemaObjImpl() );
				Buff.setOptionalCsMSSqlSchemaObjUsing( updatedBuff.getOptionalCsMSSqlSchemaObjUsing() );
				Buff.setOptionalCsMySqlSchemaObjMembers( updatedBuff.getOptionalCsMySqlSchemaObjMembers() );
				Buff.setOptionalCsMySqlSchemaObjImpl( updatedBuff.getOptionalCsMySqlSchemaObjImpl() );
				Buff.setOptionalCsMySqlSchemaObjUsing( updatedBuff.getOptionalCsMySqlSchemaObjUsing() );
				Buff.setOptionalCsOracleSchemaObjMembers( updatedBuff.getOptionalCsOracleSchemaObjMembers() );
				Buff.setOptionalCsOracleSchemaObjImpl( updatedBuff.getOptionalCsOracleSchemaObjImpl() );
				Buff.setOptionalCsOracleSchemaObjUsing( updatedBuff.getOptionalCsOracleSchemaObjUsing() );
				Buff.setOptionalCsPgSqlSchemaObjMembers( updatedBuff.getOptionalCsPgSqlSchemaObjMembers() );
				Buff.setOptionalCsPgSqlSchemaObjImpl( updatedBuff.getOptionalCsPgSqlSchemaObjImpl() );
				Buff.setOptionalCsPgSqlSchemaObjUsing( updatedBuff.getOptionalCsPgSqlSchemaObjUsing() );
				Buff.setOptionalCsRamSchemaObjMembers( updatedBuff.getOptionalCsRamSchemaObjMembers() );
				Buff.setOptionalCsRamSchemaObjImpl( updatedBuff.getOptionalCsRamSchemaObjImpl() );
				Buff.setOptionalCsRamSchemaObjUsing( updatedBuff.getOptionalCsRamSchemaObjUsing() );
				Buff.setOptionalCsXMsgSchemaUsing( updatedBuff.getOptionalCsXMsgSchemaUsing() );
				Buff.setOptionalCsXMsgSchemaFormatters( updatedBuff.getOptionalCsXMsgSchemaFormatters() );
				Buff.setOptionalCsXMsgClientSchemaUsing( updatedBuff.getOptionalCsXMsgClientSchemaUsing() );
				Buff.setOptionalCsXMsgClientSchemaBody( updatedBuff.getOptionalCsXMsgClientSchemaBody() );
				Buff.setOptionalCsXMsgRqstSchemaBody( updatedBuff.getOptionalCsXMsgRqstSchemaBody() );
				Buff.setOptionalCsXMsgRqstSchemaUsing( updatedBuff.getOptionalCsXMsgRqstSchemaUsing() );
				Buff.setOptionalCsXMsgRqstSchemaWireParsers( updatedBuff.getOptionalCsXMsgRqstSchemaWireParsers() );
				Buff.setOptionalCsXMsgRqstSchemaXsdSpec( updatedBuff.getOptionalCsXMsgRqstSchemaXsdSpec() );
				Buff.setOptionalCsXMsgRqstSchemaXsdElementList( updatedBuff.getOptionalCsXMsgRqstSchemaXsdElementList() );
				Buff.setOptionalCsXMsgRspnSchemaBody( updatedBuff.getOptionalCsXMsgRspnSchemaBody() );
				Buff.setOptionalCsXMsgRspnSchemaUsing( updatedBuff.getOptionalCsXMsgRspnSchemaUsing() );
				Buff.setOptionalCsXMsgRspnSchemaWireParsers( updatedBuff.getOptionalCsXMsgRspnSchemaWireParsers() );
				Buff.setOptionalCsXMsgRspnSchemaXsdElementList( updatedBuff.getOptionalCsXMsgRspnSchemaXsdElementList() );
				Buff.setOptionalCsXMsgRspnSchemaXsdSpec( updatedBuff.getOptionalCsXMsgRspnSchemaXsdSpec() );
				Buff.setRequiredRevision( updatedBuff.getRequiredRevision() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteSchemaDef( CFSecAuthorization Authorization,
		CFBamSchemaDefBuff Buff )
	{
		final String S_ProcName = "deleteSchemaDef";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_schemadef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
			if( stmtDeleteByPKey == null ) {
				stmtDeleteByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtDeleteByPKey.setLong( argIdx++, TenantId );
			stmtDeleteByPKey.setLong( argIdx++, Id );
			stmtDeleteByPKey.setInt( argIdx++, Buff.getRequiredRevision() );;
			resultSet = stmtDeleteByPKey.executeQuery();
			if( resultSet.next() ) {
				boolean deleteFlag = resultSet.getBoolean( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 record result set to be returned by delete, not 0 rows" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteSchemaDefByIdIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argId )
	{
		final String S_ProcName = "deleteSchemaDefByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_schemadef_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByIdIdx == null ) {
					stmtDeleteByIdIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByIdIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByIdIdx.setLong( argIdx++, argId );
				resultSet = stmtDeleteByIdIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
			}
			catch( SQLException e ) {
				throw new CFLibDbException( getClass(),
					S_ProcName,
					e );
			}
			finally {
				if( resultSet != null ) {
					try {
						resultSet.close();
					}
					catch( SQLException e ) {
					}
					resultSet = null;
				}
			}
	}

	public void deleteSchemaDefByIdIdx( CFSecAuthorization Authorization,
		CFBamScopePKey argKey )
	{
		deleteSchemaDefByIdIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredId() );
	}

	public void deleteSchemaDefByTenantIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteSchemaDefByTenantIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_schemadef_by_tenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByTenantIdx == null ) {
					stmtDeleteByTenantIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByTenantIdx.setLong( argIdx++, argTenantId );
				resultSet = stmtDeleteByTenantIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteSchemaDefByTenantIdx( CFSecAuthorization Authorization,
		CFBamScopeByTenantIdxKey argKey )
	{
		deleteSchemaDefByTenantIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteSchemaDefByCTenantIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteSchemaDefByCTenantIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_schemadef_by_ctenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByCTenantIdx == null ) {
					stmtDeleteByCTenantIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByCTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByCTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByCTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByCTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByCTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByCTenantIdx.setLong( argIdx++, argTenantId );
				resultSet = stmtDeleteByCTenantIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteSchemaDefByCTenantIdx( CFSecAuthorization Authorization,
		CFBamSchemaDefByCTenantIdxKey argKey )
	{
		deleteSchemaDefByCTenantIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteSchemaDefByMinorVersionIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argMinorVersionId )
	{
		final String S_ProcName = "deleteSchemaDefByMinorVersionIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_schemadef_by_minorversionidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByMinorVersionIdx == null ) {
					stmtDeleteByMinorVersionIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByMinorVersionIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByMinorVersionIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByMinorVersionIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByMinorVersionIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByMinorVersionIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByMinorVersionIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByMinorVersionIdx.setLong( argIdx++, argMinorVersionId );
				resultSet = stmtDeleteByMinorVersionIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteSchemaDefByMinorVersionIdx( CFSecAuthorization Authorization,
		CFBamSchemaDefByMinorVersionIdxKey argKey )
	{
		deleteSchemaDefByMinorVersionIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredMinorVersionId() );
	}

	public void deleteSchemaDefByUNameIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argMinorVersionId,
		String argName )
	{
		final String S_ProcName = "deleteSchemaDefByUNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_schemadef_by_unameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUNameIdx == null ) {
					stmtDeleteByUNameIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUNameIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByUNameIdx.setLong( argIdx++, argMinorVersionId );
				stmtDeleteByUNameIdx.setString( argIdx++, argName );
				resultSet = stmtDeleteByUNameIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteSchemaDefByUNameIdx( CFSecAuthorization Authorization,
		CFBamSchemaDefByUNameIdxKey argKey )
	{
		deleteSchemaDefByUNameIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredMinorVersionId(),
			argKey.getRequiredName() );
	}

	public void deleteSchemaDefByAuthEMailIdx( CFSecAuthorization Authorization,
		long argTenantId,
		String argAuthorEMail )
	{
		final String S_ProcName = "deleteSchemaDefByAuthEMailIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_schemadef_by_authemailidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByAuthEMailIdx == null ) {
					stmtDeleteByAuthEMailIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByAuthEMailIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByAuthEMailIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByAuthEMailIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByAuthEMailIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByAuthEMailIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByAuthEMailIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByAuthEMailIdx.setString( argIdx++, argAuthorEMail );
				resultSet = stmtDeleteByAuthEMailIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteSchemaDefByAuthEMailIdx( CFSecAuthorization Authorization,
		CFBamSchemaDefByAuthEMailIdxKey argKey )
	{
		deleteSchemaDefByAuthEMailIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredAuthorEMail() );
	}

	public void deleteSchemaDefByProjectURLIdx( CFSecAuthorization Authorization,
		long argTenantId,
		String argProjectURL )
	{
		final String S_ProcName = "deleteSchemaDefByProjectURLIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_schemadef_by_projecturlidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByProjectURLIdx == null ) {
					stmtDeleteByProjectURLIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByProjectURLIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByProjectURLIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByProjectURLIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByProjectURLIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByProjectURLIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByProjectURLIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByProjectURLIdx.setString( argIdx++, argProjectURL );
				resultSet = stmtDeleteByProjectURLIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteSchemaDefByProjectURLIdx( CFSecAuthorization Authorization,
		CFBamSchemaDefByProjectURLIdxKey argKey )
	{
		deleteSchemaDefByProjectURLIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredProjectURL() );
	}

	public void deleteSchemaDefByPubURIIdx( CFSecAuthorization Authorization,
		long argTenantId,
		String argPublishURI )
	{
		final String S_ProcName = "deleteSchemaDefByPubURIIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_schemadef_by_puburiidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByPubURIIdx == null ) {
					stmtDeleteByPubURIIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByPubURIIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByPubURIIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByPubURIIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByPubURIIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByPubURIIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByPubURIIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByPubURIIdx.setString( argIdx++, argPublishURI );
				resultSet = stmtDeleteByPubURIIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteSchemaDefByPubURIIdx( CFSecAuthorization Authorization,
		CFBamSchemaDefByPubURIIdxKey argKey )
	{
		deleteSchemaDefByPubURIIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredPublishURI() );
	}

	/**
	 *	Release the prepared statements.
	 *	<p>
	 *	When the schema changes connections, the prepared statements
	 *	have to be released because they contain connection-specific
	 *	information for most databases.
	 */
	public void releasePreparedStatements() {
		final String S_ProcName = "releasePreparedStatements";
		S_sqlSelectSchemaDefDistinctClassCode = null;
		S_sqlSelectSchemaDefBuff = null;

		if( stmtReadBuffByPKey != null ) {
			try {
				stmtReadBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByPKey = null;
		}
		if( stmtReadBuffAll != null ) {
			try {
				stmtReadBuffAll.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffAll = null;
		}
		if( stmtLockBuffByPKey != null ) {
			try {
				stmtLockBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtLockBuffByPKey = null;
		}
		if( stmtCreateByPKey != null ) {
			try {
				stmtCreateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtCreateByPKey = null;
		}
		if( stmtUpdateByPKey != null ) {
			try {
				stmtUpdateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtUpdateByPKey = null;
		}
		if( stmtDeleteByPKey != null ) {
			try {
				stmtDeleteByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByPKey = null;
		}
		if( stmtDeleteByIdIdx != null ) {
			try {
				stmtDeleteByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByIdIdx = null;
		}
		if( stmtDeleteByTenantIdx != null ) {
			try {
				stmtDeleteByTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByTenantIdx = null;
		}
		if( stmtDeleteByCTenantIdx != null ) {
			try {
				stmtDeleteByCTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByCTenantIdx = null;
		}
		if( stmtDeleteByMinorVersionIdx != null ) {
			try {
				stmtDeleteByMinorVersionIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByMinorVersionIdx = null;
		}
		if( stmtDeleteByUNameIdx != null ) {
			try {
				stmtDeleteByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUNameIdx = null;
		}
		if( stmtDeleteByAuthEMailIdx != null ) {
			try {
				stmtDeleteByAuthEMailIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByAuthEMailIdx = null;
		}
		if( stmtDeleteByProjectURLIdx != null ) {
			try {
				stmtDeleteByProjectURLIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByProjectURLIdx = null;
		}
		if( stmtDeleteByPubURIIdx != null ) {
			try {
				stmtDeleteByPubURIIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByPubURIIdx = null;
		}
		if( stmtReadAllBuff != null ) {
			try {
				stmtReadAllBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtReadAllBuff = null;
		}
		if( stmtReadBuffByIdIdx != null ) {
			try {
				stmtReadBuffByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByIdIdx = null;
		}
		if( stmtReadBuffByTenantIdx != null ) {
			try {
				stmtReadBuffByTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByTenantIdx = null;
		}
		if( stmtReadBuffByCTenantIdx != null ) {
			try {
				stmtReadBuffByCTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByCTenantIdx = null;
		}
		if( stmtReadBuffByMinorVersionIdx != null ) {
			try {
				stmtReadBuffByMinorVersionIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByMinorVersionIdx = null;
		}
		if( stmtReadBuffByUNameIdx != null ) {
			try {
				stmtReadBuffByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUNameIdx = null;
		}
		if( stmtReadBuffByAuthEMailIdx != null ) {
			try {
				stmtReadBuffByAuthEMailIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByAuthEMailIdx = null;
		}
		if( stmtReadBuffByProjectURLIdx != null ) {
			try {
				stmtReadBuffByProjectURLIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByProjectURLIdx = null;
		}
		if( stmtReadBuffByPubURIIdx != null ) {
			try {
				stmtReadBuffByPubURIIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByPubURIIdx = null;
		}
	}
}
