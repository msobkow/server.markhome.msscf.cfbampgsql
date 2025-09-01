// Description: Java 11 PostgreSQL Jdbc DbIO implementation for BlobCol.

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
 *	CFBamPgSqlBlobColTable PostgreSQL Jdbc DbIO implementation
 *	for BlobCol.
 */
public class CFBamPgSqlBlobColTable
	implements ICFBamBlobColTable
{
	private CFBamPgSqlSchema schema;
	protected PreparedStatement stmtReadBuffByPKey = null;
	protected PreparedStatement stmtReadBuffAll = null;
	protected PreparedStatement stmtLockBuffByPKey = null;
	protected PreparedStatement stmtCreateByPKey = null;
	protected PreparedStatement stmtUpdateByPKey = null;
	protected PreparedStatement stmtDeleteByPKey = null;
	protected PreparedStatement stmtAuditCreatedByPKey = null;
	protected PreparedStatement stmtAuditUpdatedByPKey = null;
	protected PreparedStatement stmtReadAllBuff = null;
	protected PreparedStatement stmtReadBuffByIdIdx = null;
	protected PreparedStatement stmtReadBuffByUNameIdx = null;
	protected PreparedStatement stmtReadBuffByValTentIdx = null;
	protected PreparedStatement stmtReadBuffByScopeIdx = null;
	protected PreparedStatement stmtReadBuffByDefSchemaIdx = null;
	protected PreparedStatement stmtReadBuffByPrevIdx = null;
	protected PreparedStatement stmtReadBuffByNextIdx = null;
	protected PreparedStatement stmtReadBuffByContPrevIdx = null;
	protected PreparedStatement stmtReadBuffByContNextIdx = null;
	protected PreparedStatement stmtReadBuffByTableIdx = null;
	protected PreparedStatement stmtMoveUpBuff = null;
	protected PreparedStatement stmtMoveDownBuff = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByUNameIdx = null;
	protected PreparedStatement stmtDeleteByValTentIdx = null;
	protected PreparedStatement stmtDeleteByScopeIdx = null;
	protected PreparedStatement stmtDeleteByDefSchemaIdx = null;
	protected PreparedStatement stmtDeleteByPrevIdx = null;
	protected PreparedStatement stmtDeleteByNextIdx = null;
	protected PreparedStatement stmtDeleteByContPrevIdx = null;
	protected PreparedStatement stmtDeleteByContNextIdx = null;
	protected PreparedStatement stmtDeleteByTableIdx = null;

	public CFBamPgSqlBlobColTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createBlobCol( CFSecAuthorization Authorization,
		CFBamBlobColBuff Buff )
	{
		final String S_ProcName = "createBlobCol";
		if( "a852".equals( Buff.getClassCode() )
			&& ( ! schema.isTenantUser( Authorization,
				Buff.getRequiredTenantId(),
				"CreateBlobCol" ) ) )
		{
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Permission denied -- User not part of TSecGroup CreateBlobCol" );
		}
		schema.getTableBlobDef().createBlobDef( Authorization, Buff );
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();
			long TableId = Buff.getRequiredTableId();
			String sql =
					"INSERT INTO " + schema.getLowerDbSchemaName() + ".blbcol( "
				+		"tenantid, "
				+		"id, "
				+		"tableid" + " )"
				+	"VALUES ( "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + " )";
			if( stmtCreateByPKey == null ) {
				stmtCreateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtCreateByPKey.setLong( argIdx++, TenantId );
			stmtCreateByPKey.setLong( argIdx++, Id );
			stmtCreateByPKey.setLong( argIdx++, TableId );
			int rowsAffected = stmtCreateByPKey.executeUpdate();
			if( rowsAffected != 1 ) {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 row to be affected by insert, not " + rowsAffected );
			}
			if( "a852".equals( Buff.getClassCode() ) ) {
				String sqlAuditCreated =
						"INSERT INTO " + schema.getLowerDbSchemaName() + ".blbcol_h( auditclusterid, "
					+		" auditsessionid,"
					+		" auditstamp,"
					+		" classcode" + ", "
					+		"TenantId" + ", "
					+		"Id" + ", "
					+		"ScopeId" + ", "
					+		"defschtentid" + ", "
					+		"defschid" + ", "
					+		"safe_name" + ", "
					+		"short_name" + ", "
					+		"Label" + ", "
					+		"short_descr" + ", "
					+		"descr" + ", "
					+		"dflt_xml_val" + ", "
					+		"IsNullable" + ", "
					+		"GenerateId" + ", "
					+		"ImplementsPolymorph" + ", "
					+		"PrevTenantId" + ", "
					+		"PrevId" + ", "
					+		"NextTenantId" + ", "
					+		"NextId" + ", "
					+		"DbName" + ", "
					+		"db_max_len" + ", "
					+		"InitVal" + ", "
					+		"TableId" + ", "
					+		" revision, "
					+		" auditaction ) "
					+	"SELECT ?, ?, now(), a809.classcode" + ", "
					+		"a809.TenantId"  + ", "
					+		"a809.Id"  + ", "
					+		"a809.ScopeId" + ", "
					+		"a809.defschtentid" + ", "
					+		"a809.defschid" + ", "
					+		"a809.safe_name" + ", "
					+		"a809.short_name" + ", "
					+		"a809.Label" + ", "
					+		"a809.short_descr" + ", "
					+		"a809.descr" + ", "
					+		"a809.dflt_xml_val" + ", "
					+		"a809.IsNullable" + ", "
					+		"a809.GenerateId" + ", "
					+		"a809.ImplementsPolymorph" + ", "
					+		"a809.PrevTenantId" + ", "
					+		"a809.PrevId" + ", "
					+		"a809.NextTenantId" + ", "
					+		"a809.NextId" + ", "
					+		"a80a.DbName" + ", "
					+		"a80b.db_max_len" + ", "
					+		"a80b.InitVal" + ", "
					+		"a852.TableId" + ", "
					+		" a809.revision, "
					+		" 0 "
					+	"FROM " + schema.getLowerDbSchemaName() + ".valdef AS a809 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".atomdef a80a ON "
					+		"a80a.TenantId = a809.TenantId "
					+		"AND a80a.Id = a809.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".blbdef a80b ON "
					+		"a80b.TenantId = a809.TenantId "
					+		"AND a80b.Id = a809.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".blbcol a852 ON "
					+		"a852.TenantId = a809.TenantId "
					+		"AND a852.Id = a809.Id "
					+	" WHERE "
					+				"a809.TenantId = ? "
					+			"AND a809.Id = ? ";
				if( stmtAuditCreatedByPKey == null ) {
					stmtAuditCreatedByPKey = cnx.prepareStatement( sqlAuditCreated );
				}
				argIdx = 1;
				stmtAuditCreatedByPKey.setLong( argIdx++, Authorization.getSecClusterId() );
				stmtAuditCreatedByPKey.setString( argIdx++, Authorization.getSecSessionId().toString() );
				stmtAuditCreatedByPKey.setLong( argIdx++, TenantId );
				stmtAuditCreatedByPKey.setLong( argIdx++, Id );
				int rowsAudited = stmtAuditCreatedByPKey.executeUpdate();
				if( rowsAudited != 1 ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 row to be affected by audit via insert-selected, not " + rowsAffected );
				}
			}
			if( "a852".equals( Buff.getClassCode() ) ) {
				CFBamValuePKey key = schema.getFactoryValue().newPKey();
				key.setRequiredTenantId( Buff.getRequiredTenantId() );
				key.setRequiredId( Buff.getRequiredId() );
				CFBamBlobColBuff reread = readBuff( Authorization, key );
				Buff.set( reread );
			}

		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
	}

	public String S_sqlSelectBlobColDistinctClassCode = null;

	public String getSqlSelectBlobColDistinctClassCode() {
		if( S_sqlSelectBlobColDistinctClassCode == null ) {
			S_sqlSelectBlobColDistinctClassCode =
					"SELECT "
					+		"DISTINCT a809.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".valdef AS a809 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".atomdef a80a ON "
					+		"a80a.TenantId = a809.TenantId "
					+		"AND a80a.Id = a809.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".blbdef a80b ON "
					+		"a80b.TenantId = a809.TenantId "
					+		"AND a80b.Id = a809.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".blbcol a852 ON "
					+		"a852.TenantId = a809.TenantId "
					+		"AND a852.Id = a809.Id ";
		}
		return( S_sqlSelectBlobColDistinctClassCode );
	}

	public String S_sqlSelectBlobColBuff = null;

	public String getSqlSelectBlobColBuff() {
		if( S_sqlSelectBlobColBuff == null ) {
			S_sqlSelectBlobColBuff =
					"SELECT "
					+		"a809.ClassCode, "
					+		"a809.TenantId, "
					+		"a809.Id, "
					+		"a809.ScopeId, "
					+		"a809.defschtentid, "
					+		"a809.defschid, "
					+		"a809.safe_name, "
					+		"a809.short_name, "
					+		"a809.Label, "
					+		"a809.short_descr, "
					+		"a809.descr, "
					+		"a809.dflt_xml_val, "
					+		"a809.IsNullable, "
					+		"a809.GenerateId, "
					+		"a809.ImplementsPolymorph, "
					+		"a809.PrevTenantId, "
					+		"a809.PrevId, "
					+		"a809.NextTenantId, "
					+		"a809.NextId, "
					+		"a80a.DbName, "
					+		"a80b.db_max_len, "
					+		"a80b.InitVal, "
					+		"a852.TableId, "
					+		"a809.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".valdef AS a809 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".atomdef a80a ON "
					+		"a80a.TenantId = a809.TenantId "
					+		"AND a80a.Id = a809.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".blbdef a80b ON "
					+		"a80b.TenantId = a809.TenantId "
					+		"AND a80b.Id = a809.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".blbcol a852 ON "
					+		"a852.TenantId = a809.TenantId "
					+		"AND a852.Id = a809.Id ";
		}
		return( S_sqlSelectBlobColBuff );
	}

	protected CFBamBlobColBuff unpackBlobColResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackBlobColResultSetToBuff";
		int idxcol = 1;
		String classCode = resultSet.getString( idxcol );
		idxcol++;
		CFBamBlobColBuff buff;
		if( classCode.equals( "a852" ) ) {
			buff = schema.getFactoryBlobCol().newBuff();
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
		buff.setRequiredScopeId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredId( resultSet.getLong( idxcol ) );
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
				buff.setOptionalDefaultXmlValue( null );
			}
			else {
				buff.setOptionalDefaultXmlValue( colVal );
			}
		}
		idxcol++;
		buff.setRequiredIsNullable( resultSet.getBoolean( idxcol ) );
		idxcol++;
		{
			boolean colVal = resultSet.getBoolean( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalGenerateId( null );
			}
			else {
				buff.setOptionalGenerateId( colVal );
			}
		}
		idxcol++;
		buff.setRequiredImplementsPolymorph( resultSet.getBoolean( idxcol ) );
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalPrevTenantId( null );
			}
			else {
				buff.setOptionalPrevTenantId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalPrevId( null );
			}
			else {
				buff.setOptionalPrevId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalNextTenantId( null );
			}
			else {
				buff.setOptionalNextTenantId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalNextId( null );
			}
			else {
				buff.setOptionalNextId( colVal );
			}
		}
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
		buff.setRequiredMaxLen( resultSet.getInt( idxcol ) );
		idxcol++;
		{
			byte colVal[] = resultSet.getBytes( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalInitValue( null );
			}
			else {
				buff.setOptionalInitValue( colVal );
			}
		}
		idxcol++;
		buff.setRequiredTableId( resultSet.getLong( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFBamBlobColBuff readDerived( CFSecAuthorization Authorization,
		CFBamValuePKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamBlobColBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamBlobColBuff lockDerived( CFSecAuthorization Authorization,
		CFBamValuePKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamBlobColBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamBlobColBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFBamBlobColBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFBamBlobColBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlBlobColTable.readDerivedByIdIdx() ";
		CFBamBlobColBuff buff;
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

	public CFBamBlobColBuff readDerivedByUNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		String Name )
	{
		final String S_ProcName = "CFBamPgSqlBlobColTable.readDerivedByUNameIdx() ";
		CFBamBlobColBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		return( buff );
	}

	public CFBamBlobColBuff[] readDerivedByValTentIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByValTentIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamBlobColBuff[] buffList = readBuffByValTentIdx( Authorization,
				TenantId );
		return( buffList );

	}

	public CFBamBlobColBuff[] readDerivedByScopeIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId )
	{
		final String S_ProcName = "readDerivedByScopeIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamBlobColBuff[] buffList = readBuffByScopeIdx( Authorization,
				TenantId,
				ScopeId );
		return( buffList );

	}

	public CFBamBlobColBuff[] readDerivedByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readDerivedByDefSchemaIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamBlobColBuff[] buffList = readBuffByDefSchemaIdx( Authorization,
				DefSchemaTenantId,
				DefSchemaId );
		return( buffList );

	}

	public CFBamBlobColBuff[] readDerivedByPrevIdx( CFSecAuthorization Authorization,
		Long PrevTenantId,
		Long PrevId )
	{
		final String S_ProcName = "readDerivedByPrevIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamBlobColBuff[] buffList = readBuffByPrevIdx( Authorization,
				PrevTenantId,
				PrevId );
		return( buffList );

	}

	public CFBamBlobColBuff[] readDerivedByNextIdx( CFSecAuthorization Authorization,
		Long NextTenantId,
		Long NextId )
	{
		final String S_ProcName = "readDerivedByNextIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamBlobColBuff[] buffList = readBuffByNextIdx( Authorization,
				NextTenantId,
				NextId );
		return( buffList );

	}

	public CFBamBlobColBuff[] readDerivedByContPrevIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		Long PrevId )
	{
		final String S_ProcName = "readDerivedByContPrevIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamBlobColBuff[] buffList = readBuffByContPrevIdx( Authorization,
				TenantId,
				ScopeId,
				PrevId );
		return( buffList );

	}

	public CFBamBlobColBuff[] readDerivedByContNextIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		Long NextId )
	{
		final String S_ProcName = "readDerivedByContNextIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamBlobColBuff[] buffList = readBuffByContNextIdx( Authorization,
				TenantId,
				ScopeId,
				NextId );
		return( buffList );

	}

	public CFBamBlobColBuff[] readDerivedByTableIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TableId )
	{
		final String S_ProcName = "readDerivedByTableIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamBlobColBuff[] buffList = readBuffByTableIdx( Authorization,
				TenantId,
				TableId );
		return( buffList );

	}

	public CFBamBlobColBuff readBuff( CFSecAuthorization Authorization,
		CFBamValuePKey PKey )
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol( ?, ?, ?, ?, ?" + ", "
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
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
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

	public CFBamBlobColBuff lockBuff( CFSecAuthorization Authorization,
		CFBamValuePKey PKey )
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_blbcol( ?, ?, ?, ?, ?" + ", "
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
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
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

	public CFBamBlobColBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_all( ?, ?, ?, ?, ? )";
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
			List<CFBamBlobColBuff> buffList = new LinkedList<CFBamBlobColBuff>();
			while( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamBlobColBuff[] retBuff = new CFBamBlobColBuff[ buffList.size() ];
			Iterator<CFBamBlobColBuff> iter = buffList.iterator();
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

	public CFBamBlobColBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_by_ididx( ?, ?, ?, ?, ?" + ", "
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
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
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

	public CFBamBlobColBuff readBuffByUNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		String Name )
	{
		final String S_ProcName = "readBuffByUNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_by_unameidx( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByUNameIdx.setLong( argIdx++, ScopeId );
			stmtReadBuffByUNameIdx.setString( argIdx++, Name );
			resultSet = stmtReadBuffByUNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
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

	public CFBamBlobColBuff[] readBuffByValTentIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByValTentIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_by_valtentidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByValTentIdx == null ) {
				stmtReadBuffByValTentIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByValTentIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByValTentIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByValTentIdx.setLong( argIdx++, TenantId );
			resultSet = stmtReadBuffByValTentIdx.executeQuery();
			List<CFBamBlobColBuff> buffList = new LinkedList<CFBamBlobColBuff>();
			while( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamBlobColBuff[] retBuff = new CFBamBlobColBuff[ buffList.size() ];
			Iterator<CFBamBlobColBuff> iter = buffList.iterator();
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

	public CFBamBlobColBuff[] readBuffByScopeIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId )
	{
		final String S_ProcName = "readBuffByScopeIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_by_scopeidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByScopeIdx == null ) {
				stmtReadBuffByScopeIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByScopeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByScopeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByScopeIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByScopeIdx.setLong( argIdx++, ScopeId );
			resultSet = stmtReadBuffByScopeIdx.executeQuery();
			List<CFBamBlobColBuff> buffList = new LinkedList<CFBamBlobColBuff>();
			while( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamBlobColBuff[] retBuff = new CFBamBlobColBuff[ buffList.size() ];
			Iterator<CFBamBlobColBuff> iter = buffList.iterator();
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

	public CFBamBlobColBuff[] readBuffByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readBuffByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
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
			List<CFBamBlobColBuff> buffList = new LinkedList<CFBamBlobColBuff>();
			while( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamBlobColBuff[] retBuff = new CFBamBlobColBuff[ buffList.size() ];
			Iterator<CFBamBlobColBuff> iter = buffList.iterator();
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

	public CFBamBlobColBuff[] readBuffByPrevIdx( CFSecAuthorization Authorization,
		Long PrevTenantId,
		Long PrevId )
	{
		final String S_ProcName = "readBuffByPrevIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_by_previdx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByPrevIdx == null ) {
				stmtReadBuffByPrevIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( PrevTenantId != null ) {
				stmtReadBuffByPrevIdx.setLong( argIdx++, PrevTenantId.longValue() );
			}
			else {
				stmtReadBuffByPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( PrevId != null ) {
				stmtReadBuffByPrevIdx.setLong( argIdx++, PrevId.longValue() );
			}
			else {
				stmtReadBuffByPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByPrevIdx.executeQuery();
			List<CFBamBlobColBuff> buffList = new LinkedList<CFBamBlobColBuff>();
			while( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamBlobColBuff[] retBuff = new CFBamBlobColBuff[ buffList.size() ];
			Iterator<CFBamBlobColBuff> iter = buffList.iterator();
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

	public CFBamBlobColBuff[] readBuffByNextIdx( CFSecAuthorization Authorization,
		Long NextTenantId,
		Long NextId )
	{
		final String S_ProcName = "readBuffByNextIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_by_nextidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByNextIdx == null ) {
				stmtReadBuffByNextIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( NextTenantId != null ) {
				stmtReadBuffByNextIdx.setLong( argIdx++, NextTenantId.longValue() );
			}
			else {
				stmtReadBuffByNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( NextId != null ) {
				stmtReadBuffByNextIdx.setLong( argIdx++, NextId.longValue() );
			}
			else {
				stmtReadBuffByNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByNextIdx.executeQuery();
			List<CFBamBlobColBuff> buffList = new LinkedList<CFBamBlobColBuff>();
			while( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamBlobColBuff[] retBuff = new CFBamBlobColBuff[ buffList.size() ];
			Iterator<CFBamBlobColBuff> iter = buffList.iterator();
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

	public CFBamBlobColBuff[] readBuffByContPrevIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		Long PrevId )
	{
		final String S_ProcName = "readBuffByContPrevIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_by_contprevidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByContPrevIdx == null ) {
				stmtReadBuffByContPrevIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByContPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByContPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByContPrevIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByContPrevIdx.setLong( argIdx++, ScopeId );
			if( PrevId != null ) {
				stmtReadBuffByContPrevIdx.setLong( argIdx++, PrevId.longValue() );
			}
			else {
				stmtReadBuffByContPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByContPrevIdx.executeQuery();
			List<CFBamBlobColBuff> buffList = new LinkedList<CFBamBlobColBuff>();
			while( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamBlobColBuff[] retBuff = new CFBamBlobColBuff[ buffList.size() ];
			Iterator<CFBamBlobColBuff> iter = buffList.iterator();
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

	public CFBamBlobColBuff[] readBuffByContNextIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		Long NextId )
	{
		final String S_ProcName = "readBuffByContNextIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_by_contnextidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByContNextIdx == null ) {
				stmtReadBuffByContNextIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByContNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByContNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByContNextIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByContNextIdx.setLong( argIdx++, ScopeId );
			if( NextId != null ) {
				stmtReadBuffByContNextIdx.setLong( argIdx++, NextId.longValue() );
			}
			else {
				stmtReadBuffByContNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByContNextIdx.executeQuery();
			List<CFBamBlobColBuff> buffList = new LinkedList<CFBamBlobColBuff>();
			while( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamBlobColBuff[] retBuff = new CFBamBlobColBuff[ buffList.size() ];
			Iterator<CFBamBlobColBuff> iter = buffList.iterator();
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

	public CFBamBlobColBuff[] readBuffByTableIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TableId )
	{
		final String S_ProcName = "readBuffByTableIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_blbcol_by_tableidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByTableIdx == null ) {
				stmtReadBuffByTableIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByTableIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByTableIdx.setLong( argIdx++, TableId );
			resultSet = stmtReadBuffByTableIdx.executeQuery();
			List<CFBamBlobColBuff> buffList = new LinkedList<CFBamBlobColBuff>();
			while( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamBlobColBuff[] retBuff = new CFBamBlobColBuff[ buffList.size() ];
			Iterator<CFBamBlobColBuff> iter = buffList.iterator();
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

	public void updateBlobCol( CFSecAuthorization Authorization,
		CFBamBlobColBuff Buff )
	{
		final String S_ProcName = "updateBlobCol";
		if( "a852".equals( Buff.getClassCode() )
			&& ( ! schema.isTenantUser( Authorization,
				Buff.getRequiredTenantId(),
				"UpdateBlobCol" ) ) )
		{
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Permission denied -- User not part of TSecGroup UpdateBlobCol" );
		}
		schema.getTableBlobDef().updateBlobDef( Authorization, Buff );
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();
			long TableId = Buff.getRequiredTableId();
			int Revision = Buff.getRequiredRevision();

			String sql =
					"UPDATE " + schema.getLowerDbSchemaName() + ".blbcol "
				+	"SET "
					+		"TenantId = ?" + ", "
					+		"Id = ?" + ", "
					+		"TableId = ?"
				+	"WHERE "
					+		"TenantId = ? "
					+	"AND "
					+		"Id = ? ";
			if( stmtUpdateByPKey == null ) {
				stmtUpdateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;

			stmtUpdateByPKey.setLong( argIdx++, TenantId );
			stmtUpdateByPKey.setLong( argIdx++, Id );
			stmtUpdateByPKey.setLong( argIdx++, TableId );
			stmtUpdateByPKey.setLong( argIdx++, TenantId );
			stmtUpdateByPKey.setLong( argIdx++, Id );;
			int rowsAffected = stmtUpdateByPKey.executeUpdate();
			if( rowsAffected != 1 ) {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 row to be affected by update, not " + rowsAffected );
			}
			if( "a852".equals( Buff.getClassCode() ) ) {
				String sqlAuditUpdated =
						"INSERT INTO " + schema.getLowerDbSchemaName() + ".blbcol_h( auditclusterid, "
					+		" auditsessionid, "
					+		" auditstamp, "
					+		" classcode" + ", "
					+		"TenantId" + ", "
					+		"Id" + ", "
					+		"ScopeId" + ", "
					+		"defschtentid" + ", "
					+		"defschid" + ", "
					+		"safe_name" + ", "
					+		"short_name" + ", "
					+		"Label" + ", "
					+		"short_descr" + ", "
					+		"descr" + ", "
					+		"dflt_xml_val" + ", "
					+		"IsNullable" + ", "
					+		"GenerateId" + ", "
					+		"ImplementsPolymorph" + ", "
					+		"PrevTenantId" + ", "
					+		"PrevId" + ", "
					+		"NextTenantId" + ", "
					+		"NextId" + ", "
					+		"DbName" + ", "
					+		"db_max_len" + ", "
					+		"InitVal" + ", "
					+		"TableId" + ", "
					+		" revision, "
					+		" auditaction ) "
					+	"SELECT ?, ?, now(), a809.classcode" + ", "
					+		"a809.TenantId"  + ", "
					+		"a809.Id"  + ", "
					+		"a809.ScopeId" + ", "
					+		"a809.defschtentid" + ", "
					+		"a809.defschid" + ", "
					+		"a809.safe_name" + ", "
					+		"a809.short_name" + ", "
					+		"a809.Label" + ", "
					+		"a809.short_descr" + ", "
					+		"a809.descr" + ", "
					+		"a809.dflt_xml_val" + ", "
					+		"a809.IsNullable" + ", "
					+		"a809.GenerateId" + ", "
					+		"a809.ImplementsPolymorph" + ", "
					+		"a809.PrevTenantId" + ", "
					+		"a809.PrevId" + ", "
					+		"a809.NextTenantId" + ", "
					+		"a809.NextId" + ", "
					+		"a80a.DbName" + ", "
					+		"a80b.db_max_len" + ", "
					+		"a80b.InitVal" + ", "
					+		"a852.TableId" + ", "
					+		" a809.revision, "
					+		" 1 "
					+	"FROM " + schema.getLowerDbSchemaName() + ".valdef AS a809 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".atomdef a80a ON "
					+		"a80a.TenantId = a809.TenantId "
					+		"AND a80a.Id = a809.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".blbdef a80b ON "
					+		"a80b.TenantId = a809.TenantId "
					+		"AND a80b.Id = a809.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".blbcol a852 ON "
					+		"a852.TenantId = a809.TenantId "
					+		"AND a852.Id = a809.Id "
					+	" WHERE "
					+				"a809.TenantId = ? "
					+			"AND a809.Id = ? ";
				if( stmtAuditUpdatedByPKey == null ) {
					stmtAuditUpdatedByPKey = cnx.prepareStatement( sqlAuditUpdated );
				}
				argIdx = 1;
				stmtAuditUpdatedByPKey.setLong( argIdx++, Authorization.getSecClusterId() );
				stmtAuditUpdatedByPKey.setString( argIdx++, Authorization.getSecSessionId().toString() );
				stmtAuditUpdatedByPKey.setLong( argIdx++, TenantId );
				stmtAuditUpdatedByPKey.setLong( argIdx++, Id );
				int rowsAudited = stmtAuditUpdatedByPKey.executeUpdate();
				if( rowsAudited != 1 ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 row to be affected by audit via insert-selected, not " + rowsAffected );
				}
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
	}

	public void deleteBlobCol( CFSecAuthorization Authorization,
		CFBamBlobColBuff Buff )
	{
		final String S_ProcName = "deleteBlobCol";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol( ?, ?, ?, ?, ?" + ", "
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

	public void deleteBlobColByIdIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argId )
	{
		final String S_ProcName = "deleteBlobColByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol_by_ididx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteBlobColByIdIdx( CFSecAuthorization Authorization,
		CFBamValuePKey argKey )
	{
		deleteBlobColByIdIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredId() );
	}

	public void deleteBlobColByUNameIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argScopeId,
		String argName )
	{
		final String S_ProcName = "deleteBlobColByUNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol_by_unameidx( ?, ?, ?, ?, ?" + ", "
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
				stmtDeleteByUNameIdx.setLong( argIdx++, argScopeId );
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

	public void deleteBlobColByUNameIdx( CFSecAuthorization Authorization,
		CFBamValueByUNameIdxKey argKey )
	{
		deleteBlobColByUNameIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredScopeId(),
			argKey.getRequiredName() );
	}

	public void deleteBlobColByValTentIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteBlobColByValTentIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol_by_valtentidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByValTentIdx == null ) {
					stmtDeleteByValTentIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByValTentIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByValTentIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByValTentIdx.setLong( argIdx++, argTenantId );
				resultSet = stmtDeleteByValTentIdx.executeQuery();
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

	public void deleteBlobColByValTentIdx( CFSecAuthorization Authorization,
		CFBamValueByValTentIdxKey argKey )
	{
		deleteBlobColByValTentIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteBlobColByScopeIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argScopeId )
	{
		final String S_ProcName = "deleteBlobColByScopeIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol_by_scopeidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByScopeIdx == null ) {
					stmtDeleteByScopeIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByScopeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByScopeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByScopeIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByScopeIdx.setLong( argIdx++, argScopeId );
				resultSet = stmtDeleteByScopeIdx.executeQuery();
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

	public void deleteBlobColByScopeIdx( CFSecAuthorization Authorization,
		CFBamValueByScopeIdxKey argKey )
	{
		deleteBlobColByScopeIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredScopeId() );
	}

	public void deleteBlobColByDefSchemaIdx( CFSecAuthorization Authorization,
		Long argDefSchemaTenantId,
		Long argDefSchemaId )
	{
		final String S_ProcName = "deleteBlobColByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteBlobColByDefSchemaIdx( CFSecAuthorization Authorization,
		CFBamValueByDefSchemaIdxKey argKey )
	{
		deleteBlobColByDefSchemaIdx( Authorization,
			argKey.getOptionalDefSchemaTenantId(),
			argKey.getOptionalDefSchemaId() );
	}

	public void deleteBlobColByPrevIdx( CFSecAuthorization Authorization,
		Long argPrevTenantId,
		Long argPrevId )
	{
		final String S_ProcName = "deleteBlobColByPrevIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol_by_previdx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByPrevIdx == null ) {
					stmtDeleteByPrevIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				if( argPrevTenantId != null ) {
					stmtDeleteByPrevIdx.setLong( argIdx++, argPrevTenantId.longValue() );
				}
				else {
					stmtDeleteByPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				if( argPrevId != null ) {
					stmtDeleteByPrevIdx.setLong( argIdx++, argPrevId.longValue() );
				}
				else {
					stmtDeleteByPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByPrevIdx.executeQuery();
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

	public void deleteBlobColByPrevIdx( CFSecAuthorization Authorization,
		CFBamValueByPrevIdxKey argKey )
	{
		deleteBlobColByPrevIdx( Authorization,
			argKey.getOptionalPrevTenantId(),
			argKey.getOptionalPrevId() );
	}

	public void deleteBlobColByNextIdx( CFSecAuthorization Authorization,
		Long argNextTenantId,
		Long argNextId )
	{
		final String S_ProcName = "deleteBlobColByNextIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol_by_nextidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByNextIdx == null ) {
					stmtDeleteByNextIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				if( argNextTenantId != null ) {
					stmtDeleteByNextIdx.setLong( argIdx++, argNextTenantId.longValue() );
				}
				else {
					stmtDeleteByNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				if( argNextId != null ) {
					stmtDeleteByNextIdx.setLong( argIdx++, argNextId.longValue() );
				}
				else {
					stmtDeleteByNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByNextIdx.executeQuery();
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

	public void deleteBlobColByNextIdx( CFSecAuthorization Authorization,
		CFBamValueByNextIdxKey argKey )
	{
		deleteBlobColByNextIdx( Authorization,
			argKey.getOptionalNextTenantId(),
			argKey.getOptionalNextId() );
	}

	public void deleteBlobColByContPrevIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argScopeId,
		Long argPrevId )
	{
		final String S_ProcName = "deleteBlobColByContPrevIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol_by_contprevidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByContPrevIdx == null ) {
					stmtDeleteByContPrevIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByContPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByContPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByContPrevIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByContPrevIdx.setLong( argIdx++, argScopeId );
				if( argPrevId != null ) {
					stmtDeleteByContPrevIdx.setLong( argIdx++, argPrevId.longValue() );
				}
				else {
					stmtDeleteByContPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByContPrevIdx.executeQuery();
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

	public void deleteBlobColByContPrevIdx( CFSecAuthorization Authorization,
		CFBamValueByContPrevIdxKey argKey )
	{
		deleteBlobColByContPrevIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredScopeId(),
			argKey.getOptionalPrevId() );
	}

	public void deleteBlobColByContNextIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argScopeId,
		Long argNextId )
	{
		final String S_ProcName = "deleteBlobColByContNextIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol_by_contnextidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByContNextIdx == null ) {
					stmtDeleteByContNextIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByContNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByContNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByContNextIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByContNextIdx.setLong( argIdx++, argScopeId );
				if( argNextId != null ) {
					stmtDeleteByContNextIdx.setLong( argIdx++, argNextId.longValue() );
				}
				else {
					stmtDeleteByContNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByContNextIdx.executeQuery();
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

	public void deleteBlobColByContNextIdx( CFSecAuthorization Authorization,
		CFBamValueByContNextIdxKey argKey )
	{
		deleteBlobColByContNextIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredScopeId(),
			argKey.getOptionalNextId() );
	}

	public void deleteBlobColByTableIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argTableId )
	{
		final String S_ProcName = "deleteBlobColByTableIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_blbcol_by_tableidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByTableIdx == null ) {
					stmtDeleteByTableIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByTableIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByTableIdx.setLong( argIdx++, argTableId );
				resultSet = stmtDeleteByTableIdx.executeQuery();
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

	public void deleteBlobColByTableIdx( CFSecAuthorization Authorization,
		CFBamBlobColByTableIdxKey argKey )
	{
		deleteBlobColByTableIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredTableId() );
	}

	/**
	 *	Move the specified buffer up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed buffer after it has been moved
	 */
	public CFBamBlobColBuff moveBuffUp( CFSecAuthorization Authorization,
		long TenantId,
		long Id,
		int argRevision )
	{
		final String S_ProcName = "moveBuffUp";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_moveup_blbcol( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", ? )";
			if( stmtMoveUpBuff == null ) {
				stmtMoveUpBuff = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtMoveUpBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtMoveUpBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtMoveUpBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtMoveUpBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtMoveUpBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtMoveUpBuff.setLong( argIdx++, TenantId );
			stmtMoveUpBuff.setLong( argIdx++, Id );
			stmtMoveUpBuff.setInt( argIdx++, argRevision );
			resultSet = stmtMoveUpBuff.executeQuery();
			if( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
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

	/**
	 *	Move the specified buffer down in the chain (i.e. to the next position.)
	 *
	 *	@return	The refreshed buffer after it has been moved
	 */
	public CFBamBlobColBuff moveBuffDown( CFSecAuthorization Authorization,
		long TenantId,
		long Id,
		int argRevision )
	{
		final String S_ProcName = "moveBuffDown";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_movedown_blbcol( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", ? )";
			if( stmtMoveDownBuff == null ) {
				stmtMoveDownBuff = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtMoveDownBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtMoveDownBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtMoveDownBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtMoveDownBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtMoveDownBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtMoveDownBuff.setLong( argIdx++, TenantId );
			stmtMoveDownBuff.setLong( argIdx++, Id );
			stmtMoveDownBuff.setInt( argIdx++, argRevision );
			resultSet = stmtMoveDownBuff.executeQuery();
			if( resultSet.next() ) {
				CFBamBlobColBuff buff = unpackBlobColResultSetToBuff( resultSet );
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

	/**
	 *	Release the prepared statements.
	 *	<p>
	 *	When the schema changes connections, the prepared statements
	 *	have to be released because they contain connection-specific
	 *	information for most databases.
	 */
	public void releasePreparedStatements() {
		final String S_ProcName = "releasePreparedStatements";
		S_sqlSelectBlobColDistinctClassCode = null;
		S_sqlSelectBlobColBuff = null;

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
		if( stmtDeleteByUNameIdx != null ) {
			try {
				stmtDeleteByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUNameIdx = null;
		}
		if( stmtDeleteByValTentIdx != null ) {
			try {
				stmtDeleteByValTentIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByValTentIdx = null;
		}
		if( stmtDeleteByScopeIdx != null ) {
			try {
				stmtDeleteByScopeIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByScopeIdx = null;
		}
		if( stmtDeleteByDefSchemaIdx != null ) {
			try {
				stmtDeleteByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByDefSchemaIdx = null;
		}
		if( stmtDeleteByPrevIdx != null ) {
			try {
				stmtDeleteByPrevIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByPrevIdx = null;
		}
		if( stmtDeleteByNextIdx != null ) {
			try {
				stmtDeleteByNextIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByNextIdx = null;
		}
		if( stmtDeleteByContPrevIdx != null ) {
			try {
				stmtDeleteByContPrevIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByContPrevIdx = null;
		}
		if( stmtDeleteByContNextIdx != null ) {
			try {
				stmtDeleteByContNextIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByContNextIdx = null;
		}
		if( stmtDeleteByTableIdx != null ) {
			try {
				stmtDeleteByTableIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByTableIdx = null;
		}
		if( stmtAuditCreatedByPKey != null ) {
			try {
				stmtAuditCreatedByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtAuditCreatedByPKey = null;
		}
		if( stmtAuditUpdatedByPKey != null ) {
			try {
				stmtAuditUpdatedByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtAuditUpdatedByPKey = null;
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
		if( stmtReadBuffByUNameIdx != null ) {
			try {
				stmtReadBuffByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUNameIdx = null;
		}
		if( stmtReadBuffByValTentIdx != null ) {
			try {
				stmtReadBuffByValTentIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByValTentIdx = null;
		}
		if( stmtReadBuffByScopeIdx != null ) {
			try {
				stmtReadBuffByScopeIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByScopeIdx = null;
		}
		if( stmtReadBuffByDefSchemaIdx != null ) {
			try {
				stmtReadBuffByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByDefSchemaIdx = null;
		}
		if( stmtReadBuffByPrevIdx != null ) {
			try {
				stmtReadBuffByPrevIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByPrevIdx = null;
		}
		if( stmtReadBuffByNextIdx != null ) {
			try {
				stmtReadBuffByNextIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByNextIdx = null;
		}
		if( stmtReadBuffByContPrevIdx != null ) {
			try {
				stmtReadBuffByContPrevIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByContPrevIdx = null;
		}
		if( stmtReadBuffByContNextIdx != null ) {
			try {
				stmtReadBuffByContNextIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByContNextIdx = null;
		}
		if( stmtReadBuffByTableIdx != null ) {
			try {
				stmtReadBuffByTableIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByTableIdx = null;
		}
		if( stmtMoveUpBuff != null ) {
			try {
				stmtMoveUpBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtMoveUpBuff = null;
		}
		if( stmtMoveDownBuff != null ) {
			try {
				stmtMoveDownBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtMoveDownBuff = null;
		}
	}
}
