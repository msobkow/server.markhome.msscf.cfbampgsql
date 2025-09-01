// Description: Java 11 PostgreSQL Jdbc DbIO implementation for ServerListFunc.

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
 *	CFBamPgSqlServerListFuncTable PostgreSQL Jdbc DbIO implementation
 *	for ServerListFunc.
 */
public class CFBamPgSqlServerListFuncTable
	implements ICFBamServerListFuncTable
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
	protected PreparedStatement stmtReadBuffByUNameIdx = null;
	protected PreparedStatement stmtReadBuffByMethTableIdx = null;
	protected PreparedStatement stmtReadBuffByDefSchemaIdx = null;
	protected PreparedStatement stmtReadBuffByRetTblIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByTenantIdx = null;
	protected PreparedStatement stmtDeleteByUNameIdx = null;
	protected PreparedStatement stmtDeleteByMethTableIdx = null;
	protected PreparedStatement stmtDeleteByDefSchemaIdx = null;
	protected PreparedStatement stmtDeleteByRetTblIdx = null;

	public CFBamPgSqlServerListFuncTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createServerListFunc( CFSecAuthorization Authorization,
		CFBamServerListFuncBuff Buff )
	{
		final String S_ProcName = "createServerListFunc";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long TableId = Buff.getRequiredTableId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			String Name = Buff.getRequiredName();
			String ShortName = Buff.getOptionalShortName();
			String Label = Buff.getOptionalLabel();
			String ShortDescription = Buff.getOptionalShortDescription();
			String Description = Buff.getOptionalDescription();
			String Suffix = Buff.getOptionalSuffix();
			boolean IsInstanceMethod = Buff.getRequiredIsInstanceMethod();
			boolean IsServerOnly = Buff.getRequiredIsServerOnly();
			String JMethodBody = Buff.getRequiredJMethodBody();
			String CppMethodBody = Buff.getRequiredCppMethodBody();
			String CsMethodBody = Buff.getRequiredCsMethodBody();
			Long RetTenantId = Buff.getOptionalRetTenantId();
			Long RetTableId = Buff.getOptionalRetTableId();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_srvlfunc( ?, ?, ?, ?, ?, ?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
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
				+		"cast( ? as text )" + ", "
				+		"cast( ? as text )" + ", "
				+		"?" + ", "
				+		"?" + " )";
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
			stmtCreateByPKey.setLong( argIdx++, TableId );
			if( DefSchemaTenantId != null ) {
				stmtCreateByPKey.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtCreateByPKey.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			stmtCreateByPKey.setString( argIdx++, Name );
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
			if( Suffix != null ) {
				stmtCreateByPKey.setString( argIdx++, Suffix );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtCreateByPKey.setBoolean( argIdx++, IsInstanceMethod );
			stmtCreateByPKey.setBoolean( argIdx++, IsServerOnly );
			stmtCreateByPKey.setString( argIdx++, JMethodBody );
			stmtCreateByPKey.setString( argIdx++, CppMethodBody );
			stmtCreateByPKey.setString( argIdx++, CsMethodBody );
			if( RetTenantId != null ) {
				stmtCreateByPKey.setLong( argIdx++, RetTenantId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( RetTableId != null ) {
				stmtCreateByPKey.setLong( argIdx++, RetTableId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamServerListFuncBuff createdBuff = unpackServerListFuncResultSetToBuff( resultSet );
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
				Buff.setRequiredTableId( createdBuff.getRequiredTableId() );
				Buff.setOptionalDefSchemaTenantId( createdBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( createdBuff.getOptionalDefSchemaId() );
				Buff.setRequiredName( createdBuff.getRequiredName() );
				Buff.setOptionalShortName( createdBuff.getOptionalShortName() );
				Buff.setOptionalLabel( createdBuff.getOptionalLabel() );
				Buff.setOptionalShortDescription( createdBuff.getOptionalShortDescription() );
				Buff.setOptionalDescription( createdBuff.getOptionalDescription() );
				Buff.setOptionalSuffix( createdBuff.getOptionalSuffix() );
				Buff.setRequiredIsInstanceMethod( createdBuff.getRequiredIsInstanceMethod() );
				Buff.setRequiredIsServerOnly( createdBuff.getRequiredIsServerOnly() );
				Buff.setRequiredJMethodBody( createdBuff.getRequiredJMethodBody() );
				Buff.setRequiredCppMethodBody( createdBuff.getRequiredCppMethodBody() );
				Buff.setRequiredCsMethodBody( createdBuff.getRequiredCsMethodBody() );
				Buff.setOptionalRetTenantId( createdBuff.getOptionalRetTenantId() );
				Buff.setOptionalRetTableId( createdBuff.getOptionalRetTableId() );
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

	public String S_sqlSelectServerListFuncDistinctClassCode = null;

	public String getSqlSelectServerListFuncDistinctClassCode() {
		if( S_sqlSelectServerListFuncDistinctClassCode == null ) {
			S_sqlSelectServerListFuncDistinctClassCode =
					"SELECT "
					+		"DISTINCT a801.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".srvmeth a805 ON "
					+		"a805.TenantId = a801.TenantId "
					+		"AND a805.Id = a801.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".srvlfunc a837 ON "
					+		"a837.TenantId = a801.TenantId "
					+		"AND a837.Id = a801.Id ";
		}
		return( S_sqlSelectServerListFuncDistinctClassCode );
	}

	public String S_sqlSelectServerListFuncBuff = null;

	public String getSqlSelectServerListFuncBuff() {
		if( S_sqlSelectServerListFuncBuff == null ) {
			S_sqlSelectServerListFuncBuff =
					"SELECT "
					+		"a801.ClassCode, "
					+		"a801.TenantId, "
					+		"a801.Id, "
					+		"a805.TableId, "
					+		"a805.defschtentid, "
					+		"a805.defschid, "
					+		"a805.safe_name, "
					+		"a805.short_name, "
					+		"a805.Label, "
					+		"a805.short_descr, "
					+		"a805.descr, "
					+		"a805.Suffix, "
					+		"a805.inst_meth, "
					+		"a805.srv_only, "
					+		"a805.jmeth_body, "
					+		"a805.cppmeth_body, "
					+		"a805.csmeth_body, "
					+		"a837.rettentid, "
					+		"a837.rettblid, "
					+		"a801.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".srvmeth a805 ON "
					+		"a805.TenantId = a801.TenantId "
					+		"AND a805.Id = a801.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".srvlfunc a837 ON "
					+		"a837.TenantId = a801.TenantId "
					+		"AND a837.Id = a801.Id ";
		}
		return( S_sqlSelectServerListFuncBuff );
	}

	protected CFBamServerListFuncBuff unpackServerListFuncResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackServerListFuncResultSetToBuff";
		int idxcol = 1;
		String classCode = resultSet.getString( idxcol );
		idxcol++;
		CFBamServerListFuncBuff buff;
		if( classCode.equals( "a837" ) ) {
			buff = schema.getFactoryServerListFunc().newBuff();
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
		buff.setRequiredTableId( resultSet.getLong( idxcol ) );
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDefSchemaTenantId( null );
			}
			else {
				buff.setOptionalDefSchemaTenantId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDefSchemaId( null );
			}
			else {
				buff.setOptionalDefSchemaId( colVal );
			}
		}
		idxcol++;
		buff.setRequiredName( resultSet.getString( idxcol ) );
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
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalSuffix( null );
			}
			else {
				buff.setOptionalSuffix( colVal );
			}
		}
		idxcol++;
		buff.setRequiredIsInstanceMethod( resultSet.getBoolean( idxcol ) );
		idxcol++;
		buff.setRequiredIsServerOnly( resultSet.getBoolean( idxcol ) );
		idxcol++;
		buff.setRequiredJMethodBody( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredCppMethodBody( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredCsMethodBody( resultSet.getString( idxcol ) );
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalRetTenantId( null );
			}
			else {
				buff.setOptionalRetTenantId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalRetTableId( null );
			}
			else {
				buff.setOptionalRetTableId( colVal );
			}
		}
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFBamServerListFuncBuff readDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamServerListFuncBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamServerListFuncBuff lockDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamServerListFuncBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamServerListFuncBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFBamServerListFuncBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFBamServerListFuncBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlServerListFuncTable.readDerivedByIdIdx() ";
		CFBamServerListFuncBuff buff;
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

	public CFBamServerListFuncBuff[] readDerivedByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByTenantIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamServerListFuncBuff[] buffList = readBuffByTenantIdx( Authorization,
				TenantId );
		return( buffList );

	}

	public CFBamServerListFuncBuff readDerivedByUNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TableId,
		String Name )
	{
		final String S_ProcName = "CFBamPgSqlServerListFuncTable.readDerivedByUNameIdx() ";
		CFBamServerListFuncBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUNameIdx( Authorization,
				TenantId,
				TableId,
				Name );
		return( buff );
	}

	public CFBamServerListFuncBuff[] readDerivedByMethTableIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TableId )
	{
		final String S_ProcName = "readDerivedByMethTableIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamServerListFuncBuff[] buffList = readBuffByMethTableIdx( Authorization,
				TenantId,
				TableId );
		return( buffList );

	}

	public CFBamServerListFuncBuff[] readDerivedByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readDerivedByDefSchemaIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamServerListFuncBuff[] buffList = readBuffByDefSchemaIdx( Authorization,
				DefSchemaTenantId,
				DefSchemaId );
		return( buffList );

	}

	public CFBamServerListFuncBuff[] readDerivedByRetTblIdx( CFSecAuthorization Authorization,
		Long RetTenantId,
		Long RetTableId )
	{
		final String S_ProcName = "readDerivedByRetTblIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamServerListFuncBuff[] buffList = readBuffByRetTblIdx( Authorization,
				RetTenantId,
				RetTableId );
		return( buffList );

	}

	public CFBamServerListFuncBuff readBuff( CFSecAuthorization Authorization,
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_srvlfunc( ?, ?, ?, ?, ?" + ", "
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
				CFBamServerListFuncBuff buff = unpackServerListFuncResultSetToBuff( resultSet );
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

	public CFBamServerListFuncBuff lockBuff( CFSecAuthorization Authorization,
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_srvlfunc( ?, ?, ?, ?, ?" + ", "
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
				CFBamServerListFuncBuff buff = unpackServerListFuncResultSetToBuff( resultSet );
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

	public CFBamServerListFuncBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_srvlfunc_all( ?, ?, ?, ?, ? )";
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
			List<CFBamServerListFuncBuff> buffList = new LinkedList<CFBamServerListFuncBuff>();
			while( resultSet.next() ) {
				CFBamServerListFuncBuff buff = unpackServerListFuncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamServerListFuncBuff[] retBuff = new CFBamServerListFuncBuff[ buffList.size() ];
			Iterator<CFBamServerListFuncBuff> iter = buffList.iterator();
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

	public CFBamServerListFuncBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_srvlfunc_by_ididx( ?, ?, ?, ?, ?" + ", "
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
				CFBamServerListFuncBuff buff = unpackServerListFuncResultSetToBuff( resultSet );
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

	public CFBamServerListFuncBuff[] readBuffByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_srvlfunc_by_tenantidx( ?, ?, ?, ?, ?" + ", "
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
			List<CFBamServerListFuncBuff> buffList = new LinkedList<CFBamServerListFuncBuff>();
			while( resultSet.next() ) {
				CFBamServerListFuncBuff buff = unpackServerListFuncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamServerListFuncBuff[] retBuff = new CFBamServerListFuncBuff[ buffList.size() ];
			Iterator<CFBamServerListFuncBuff> iter = buffList.iterator();
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

	public CFBamServerListFuncBuff readBuffByUNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TableId,
		String Name )
	{
		final String S_ProcName = "readBuffByUNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_srvlfunc_by_unameidx( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByUNameIdx.setLong( argIdx++, TableId );
			stmtReadBuffByUNameIdx.setString( argIdx++, Name );
			resultSet = stmtReadBuffByUNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamServerListFuncBuff buff = unpackServerListFuncResultSetToBuff( resultSet );
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

	public CFBamServerListFuncBuff[] readBuffByMethTableIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TableId )
	{
		final String S_ProcName = "readBuffByMethTableIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_srvlfunc_by_methtableidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByMethTableIdx == null ) {
				stmtReadBuffByMethTableIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByMethTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByMethTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByMethTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByMethTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByMethTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByMethTableIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByMethTableIdx.setLong( argIdx++, TableId );
			resultSet = stmtReadBuffByMethTableIdx.executeQuery();
			List<CFBamServerListFuncBuff> buffList = new LinkedList<CFBamServerListFuncBuff>();
			while( resultSet.next() ) {
				CFBamServerListFuncBuff buff = unpackServerListFuncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamServerListFuncBuff[] retBuff = new CFBamServerListFuncBuff[ buffList.size() ];
			Iterator<CFBamServerListFuncBuff> iter = buffList.iterator();
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

	public CFBamServerListFuncBuff[] readBuffByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readBuffByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_srvlfunc_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByDefSchemaIdx == null ) {
				stmtReadBuffByDefSchemaIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( DefSchemaTenantId != null ) {
				stmtReadBuffByDefSchemaIdx.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtReadBuffByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtReadBuffByDefSchemaIdx.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtReadBuffByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByDefSchemaIdx.executeQuery();
			List<CFBamServerListFuncBuff> buffList = new LinkedList<CFBamServerListFuncBuff>();
			while( resultSet.next() ) {
				CFBamServerListFuncBuff buff = unpackServerListFuncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamServerListFuncBuff[] retBuff = new CFBamServerListFuncBuff[ buffList.size() ];
			Iterator<CFBamServerListFuncBuff> iter = buffList.iterator();
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

	public CFBamServerListFuncBuff[] readBuffByRetTblIdx( CFSecAuthorization Authorization,
		Long RetTenantId,
		Long RetTableId )
	{
		final String S_ProcName = "readBuffByRetTblIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_srvlfunc_by_rettblidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByRetTblIdx == null ) {
				stmtReadBuffByRetTblIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByRetTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByRetTblIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByRetTblIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByRetTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByRetTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( RetTenantId != null ) {
				stmtReadBuffByRetTblIdx.setLong( argIdx++, RetTenantId.longValue() );
			}
			else {
				stmtReadBuffByRetTblIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( RetTableId != null ) {
				stmtReadBuffByRetTblIdx.setLong( argIdx++, RetTableId.longValue() );
			}
			else {
				stmtReadBuffByRetTblIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByRetTblIdx.executeQuery();
			List<CFBamServerListFuncBuff> buffList = new LinkedList<CFBamServerListFuncBuff>();
			while( resultSet.next() ) {
				CFBamServerListFuncBuff buff = unpackServerListFuncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamServerListFuncBuff[] retBuff = new CFBamServerListFuncBuff[ buffList.size() ];
			Iterator<CFBamServerListFuncBuff> iter = buffList.iterator();
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

	public void updateServerListFunc( CFSecAuthorization Authorization,
		CFBamServerListFuncBuff Buff )
	{
		final String S_ProcName = "updateServerListFunc";
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();
			long TableId = Buff.getRequiredTableId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			String Name = Buff.getRequiredName();
			String ShortName = Buff.getOptionalShortName();
			String Label = Buff.getOptionalLabel();
			String ShortDescription = Buff.getOptionalShortDescription();
			String Description = Buff.getOptionalDescription();
			String Suffix = Buff.getOptionalSuffix();
			boolean IsInstanceMethod = Buff.getRequiredIsInstanceMethod();
			boolean IsServerOnly = Buff.getRequiredIsServerOnly();
			String JMethodBody = Buff.getRequiredJMethodBody();
			String CppMethodBody = Buff.getRequiredCppMethodBody();
			String CsMethodBody = Buff.getRequiredCsMethodBody();
			Long RetTenantId = Buff.getOptionalRetTenantId();
			Long RetTableId = Buff.getOptionalRetTableId();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_srvlfunc( ?, ?, ?, ?, ?, ?" + ", "
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
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"cast( ? as text )" + ", "
						+	"?" + ", "
						+	"?" + ", "
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
			stmtUpdateByPKey.setLong( argIdx++, TableId );
			if( DefSchemaTenantId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			stmtUpdateByPKey.setString( argIdx++, Name );
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
			if( Suffix != null ) {
				stmtUpdateByPKey.setString( argIdx++, Suffix );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtUpdateByPKey.setBoolean( argIdx++, IsInstanceMethod );
			stmtUpdateByPKey.setBoolean( argIdx++, IsServerOnly );
			stmtUpdateByPKey.setString( argIdx++, JMethodBody );
			stmtUpdateByPKey.setString( argIdx++, CppMethodBody );
			stmtUpdateByPKey.setString( argIdx++, CsMethodBody );
			if( RetTenantId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, RetTenantId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( RetTableId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, RetTableId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamServerListFuncBuff updatedBuff = unpackServerListFuncResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredTableId( updatedBuff.getRequiredTableId() );
				Buff.setOptionalDefSchemaTenantId( updatedBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( updatedBuff.getOptionalDefSchemaId() );
				Buff.setRequiredName( updatedBuff.getRequiredName() );
				Buff.setOptionalShortName( updatedBuff.getOptionalShortName() );
				Buff.setOptionalLabel( updatedBuff.getOptionalLabel() );
				Buff.setOptionalShortDescription( updatedBuff.getOptionalShortDescription() );
				Buff.setOptionalDescription( updatedBuff.getOptionalDescription() );
				Buff.setOptionalSuffix( updatedBuff.getOptionalSuffix() );
				Buff.setRequiredIsInstanceMethod( updatedBuff.getRequiredIsInstanceMethod() );
				Buff.setRequiredIsServerOnly( updatedBuff.getRequiredIsServerOnly() );
				Buff.setRequiredJMethodBody( updatedBuff.getRequiredJMethodBody() );
				Buff.setRequiredCppMethodBody( updatedBuff.getRequiredCppMethodBody() );
				Buff.setRequiredCsMethodBody( updatedBuff.getRequiredCsMethodBody() );
				Buff.setOptionalRetTenantId( updatedBuff.getOptionalRetTenantId() );
				Buff.setOptionalRetTableId( updatedBuff.getOptionalRetTableId() );
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

	public void deleteServerListFunc( CFSecAuthorization Authorization,
		CFBamServerListFuncBuff Buff )
	{
		final String S_ProcName = "deleteServerListFunc";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_srvlfunc( ?, ?, ?, ?, ?" + ", "
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

	public void deleteServerListFuncByIdIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argId )
	{
		final String S_ProcName = "deleteServerListFuncByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_srvlfunc_by_ididx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteServerListFuncByIdIdx( CFSecAuthorization Authorization,
		CFBamScopePKey argKey )
	{
		deleteServerListFuncByIdIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredId() );
	}

	public void deleteServerListFuncByTenantIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteServerListFuncByTenantIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_srvlfunc_by_tenantidx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteServerListFuncByTenantIdx( CFSecAuthorization Authorization,
		CFBamScopeByTenantIdxKey argKey )
	{
		deleteServerListFuncByTenantIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteServerListFuncByUNameIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argTableId,
		String argName )
	{
		final String S_ProcName = "deleteServerListFuncByUNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_srvlfunc_by_unameidx( ?, ?, ?, ?, ?" + ", "
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
				stmtDeleteByUNameIdx.setLong( argIdx++, argTableId );
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

	public void deleteServerListFuncByUNameIdx( CFSecAuthorization Authorization,
		CFBamServerMethodByUNameIdxKey argKey )
	{
		deleteServerListFuncByUNameIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredTableId(),
			argKey.getRequiredName() );
	}

	public void deleteServerListFuncByMethTableIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argTableId )
	{
		final String S_ProcName = "deleteServerListFuncByMethTableIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_srvlfunc_by_methtableidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByMethTableIdx == null ) {
					stmtDeleteByMethTableIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByMethTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByMethTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByMethTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByMethTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByMethTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByMethTableIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByMethTableIdx.setLong( argIdx++, argTableId );
				resultSet = stmtDeleteByMethTableIdx.executeQuery();
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

	public void deleteServerListFuncByMethTableIdx( CFSecAuthorization Authorization,
		CFBamServerMethodByMethTableIdxKey argKey )
	{
		deleteServerListFuncByMethTableIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredTableId() );
	}

	public void deleteServerListFuncByDefSchemaIdx( CFSecAuthorization Authorization,
		Long argDefSchemaTenantId,
		Long argDefSchemaId )
	{
		final String S_ProcName = "deleteServerListFuncByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_srvlfunc_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByDefSchemaIdx == null ) {
					stmtDeleteByDefSchemaIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				if( argDefSchemaTenantId != null ) {
					stmtDeleteByDefSchemaIdx.setLong( argIdx++, argDefSchemaTenantId.longValue() );
				}
				else {
					stmtDeleteByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				if( argDefSchemaId != null ) {
					stmtDeleteByDefSchemaIdx.setLong( argIdx++, argDefSchemaId.longValue() );
				}
				else {
					stmtDeleteByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByDefSchemaIdx.executeQuery();
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

	public void deleteServerListFuncByDefSchemaIdx( CFSecAuthorization Authorization,
		CFBamServerMethodByDefSchemaIdxKey argKey )
	{
		deleteServerListFuncByDefSchemaIdx( Authorization,
			argKey.getOptionalDefSchemaTenantId(),
			argKey.getOptionalDefSchemaId() );
	}

	public void deleteServerListFuncByRetTblIdx( CFSecAuthorization Authorization,
		Long argRetTenantId,
		Long argRetTableId )
	{
		final String S_ProcName = "deleteServerListFuncByRetTblIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_srvlfunc_by_rettblidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByRetTblIdx == null ) {
					stmtDeleteByRetTblIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByRetTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByRetTblIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByRetTblIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByRetTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByRetTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				if( argRetTenantId != null ) {
					stmtDeleteByRetTblIdx.setLong( argIdx++, argRetTenantId.longValue() );
				}
				else {
					stmtDeleteByRetTblIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				if( argRetTableId != null ) {
					stmtDeleteByRetTblIdx.setLong( argIdx++, argRetTableId.longValue() );
				}
				else {
					stmtDeleteByRetTblIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByRetTblIdx.executeQuery();
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

	public void deleteServerListFuncByRetTblIdx( CFSecAuthorization Authorization,
		CFBamServerListFuncByRetTblIdxKey argKey )
	{
		deleteServerListFuncByRetTblIdx( Authorization,
			argKey.getOptionalRetTenantId(),
			argKey.getOptionalRetTableId() );
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
		S_sqlSelectServerListFuncDistinctClassCode = null;
		S_sqlSelectServerListFuncBuff = null;

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
		if( stmtDeleteByUNameIdx != null ) {
			try {
				stmtDeleteByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUNameIdx = null;
		}
		if( stmtDeleteByMethTableIdx != null ) {
			try {
				stmtDeleteByMethTableIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByMethTableIdx = null;
		}
		if( stmtDeleteByDefSchemaIdx != null ) {
			try {
				stmtDeleteByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByDefSchemaIdx = null;
		}
		if( stmtDeleteByRetTblIdx != null ) {
			try {
				stmtDeleteByRetTblIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByRetTblIdx = null;
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
		if( stmtReadBuffByUNameIdx != null ) {
			try {
				stmtReadBuffByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUNameIdx = null;
		}
		if( stmtReadBuffByMethTableIdx != null ) {
			try {
				stmtReadBuffByMethTableIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByMethTableIdx = null;
		}
		if( stmtReadBuffByDefSchemaIdx != null ) {
			try {
				stmtReadBuffByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByDefSchemaIdx = null;
		}
		if( stmtReadBuffByRetTblIdx != null ) {
			try {
				stmtReadBuffByRetTblIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByRetTblIdx = null;
		}
	}
}
