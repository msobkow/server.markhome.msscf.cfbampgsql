// Description: Java 11 PostgreSQL Jdbc DbIO implementation for DelDep.

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
 *	CFBamPgSqlDelDepTable PostgreSQL Jdbc DbIO implementation
 *	for DelDep.
 */
public class CFBamPgSqlDelDepTable
	implements ICFBamDelDepTable
{
	private CFBamPgSqlSchema schema;
	protected PreparedStatement stmtReadBuffByPKey = null;
	protected PreparedStatement stmtReadBuffAll = null;
	protected PreparedStatement stmtLockBuffByPKey = null;
	protected PreparedStatement stmtCreateByPKey = null;
	protected PreparedStatement stmtUpdateByPKey = null;
	protected PreparedStatement stmtDeleteByPKey = null;
	protected PreparedStatement stmtReadAllBuff = null;
	protected PreparedStatement stmtReadDerivedClassCode = null;
	protected PreparedStatement stmtLockDerivedClassCode = null;
	protected PreparedStatement stmtReadAllClassCode = null;
	protected PreparedStatement stmtReadClassCodeByIdIdx = null;
	protected PreparedStatement stmtReadBuffByIdIdx = null;
	protected PreparedStatement stmtReadClassCodeByTenantIdx = null;
	protected PreparedStatement stmtReadBuffByTenantIdx = null;
	protected PreparedStatement stmtReadClassCodeByDefSchemaIdx = null;
	protected PreparedStatement stmtReadBuffByDefSchemaIdx = null;
	protected PreparedStatement stmtReadClassCodeByDelDepIdx = null;
	protected PreparedStatement stmtReadBuffByDelDepIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByTenantIdx = null;
	protected PreparedStatement stmtDeleteByDefSchemaIdx = null;
	protected PreparedStatement stmtDeleteByDelDepIdx = null;

	public CFBamPgSqlDelDepTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createDelDep( CFSecAuthorization Authorization,
		CFBamDelDepBuff Buff )
	{
		final String S_ProcName = "createDelDep";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			long RelationTenantId = Buff.getRequiredRelationTenantId();
			long RelationId = Buff.getRequiredRelationId();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_del_dep( ?, ?, ?, ?, ?, ?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
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
			stmtCreateByPKey.setLong( argIdx++, RelationTenantId );
			stmtCreateByPKey.setLong( argIdx++, RelationId );
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamDelDepBuff createdBuff = unpackDelDepResultSetToBuff( resultSet );
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
				Buff.setOptionalDefSchemaTenantId( createdBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( createdBuff.getOptionalDefSchemaId() );
				Buff.setRequiredRelationTenantId( createdBuff.getRequiredRelationTenantId() );
				Buff.setRequiredRelationId( createdBuff.getRequiredRelationId() );
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

	public String S_sqlSelectDelDepDistinctClassCode = null;

	public String getSqlSelectDelDepDistinctClassCode() {
		if( S_sqlSelectDelDepDistinctClassCode == null ) {
			S_sqlSelectDelDepDistinctClassCode =
					"SELECT "
					+		"DISTINCT a801.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".del_dep a817 ON "
					+		"a817.TenantId = a801.TenantId "
					+		"AND a817.Id = a801.Id ";
		}
		return( S_sqlSelectDelDepDistinctClassCode );
	}

	public String S_sqlSelectDelDepBuff = null;

	public String getSqlSelectDelDepBuff() {
		if( S_sqlSelectDelDepBuff == null ) {
			S_sqlSelectDelDepBuff =
					"SELECT "
					+		"a801.ClassCode, "
					+		"a801.TenantId, "
					+		"a801.Id, "
					+		"a817.defschtentid, "
					+		"a817.defschid, "
					+		"a817.RelationTenantId, "
					+		"a817.RelationId, "
					+		"a801.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".del_dep a817 ON "
					+		"a817.TenantId = a801.TenantId "
					+		"AND a817.Id = a801.Id ";
		}
		return( S_sqlSelectDelDepBuff );
	}

	protected CFBamDelDepBuff unpackDelDepResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackDelDepResultSetToBuff";
		int idxcol = 1;
		String classCode = resultSet.getString( idxcol );
		idxcol++;
		CFBamDelDepBuff buff;
		if( classCode.equals( "a817" ) ) {
			buff = schema.getFactoryDelDep().newBuff();
		}
		else if( classCode.equals( "a818" ) ) {
			buff = schema.getFactoryDelSubDep1().newBuff();
		}
		else if( classCode.equals( "a819" ) ) {
			buff = schema.getFactoryDelSubDep2().newBuff();
		}
		else if( classCode.equals( "a81a" ) ) {
			buff = schema.getFactoryDelSubDep3().newBuff();
		}
		else if( classCode.equals( "a81b" ) ) {
			buff = schema.getFactoryDelTopDep().newBuff();
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
		buff.setRequiredRelationTenantId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredRelationId( resultSet.getLong( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFBamDelDepBuff readDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamDelDepBuff buff;

		String classCode;
		ResultSet resultSet = null;
		try {
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_cc_del_dep( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadDerivedClassCode == null ) {
				stmtReadDerivedClassCode = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadDerivedClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadDerivedClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadDerivedClassCode.setLong( argIdx++, TenantId );
			stmtReadDerivedClassCode.setLong( argIdx++, Id );
			resultSet = stmtReadDerivedClassCode.executeQuery();
			if( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
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
		if( classCode.equals( "a817" ) ) {
			buff = readBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a818" ) ) {
			buff = schema.getTableDelSubDep1().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a819" ) ) {
			buff = schema.getTableDelSubDep2().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a81a" ) ) {
			buff = schema.getTableDelSubDep3().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a81b" ) ) {
			buff = schema.getTableDelTopDep().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Did not expect ClassCode \"" + classCode + "\"" );
		}
		return( buff );
	}

	public CFBamDelDepBuff lockDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamDelDepBuff buff;
		String classCode;
		ResultSet resultSet = null;
		try {
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_cc_del_dep( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtLockDerivedClassCode == null ) {
				stmtLockDerivedClassCode = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtLockDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockDerivedClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtLockDerivedClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtLockDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtLockDerivedClassCode.setLong( argIdx++, TenantId );
			stmtLockDerivedClassCode.setLong( argIdx++, Id );
			resultSet = stmtLockDerivedClassCode.executeQuery();
			if( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
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
		if( classCode.equals( "a817" ) ) {
			buff = lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a818" ) ) {
			buff = schema.getTableDelSubDep1().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a819" ) ) {
			buff = schema.getTableDelSubDep2().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a81a" ) ) {
			buff = schema.getTableDelSubDep3().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a81b" ) ) {
			buff = schema.getTableDelTopDep().lockBuff( Authorization, PKey );
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Did not expect ClassCode \"" + classCode + "\"" );
		}
		return( buff );
	}

	public CFBamDelDepBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFBamDelDepBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		String classCode;
		ArrayList<String> classCodeList = new ArrayList<String>();
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep_cc_all( ?, ?, ?, ?, ? )";
			if( stmtReadAllClassCode == null ) {
				stmtReadAllClassCode = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadAllClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadAllClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadAllClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			resultSet = stmtReadAllClassCode.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
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
		List<CFBamDelDepBuff> resultList = new LinkedList<CFBamDelDepBuff>();
		for( int classCodeIdx = 0; classCodeIdx < classCodeList.size(); classCodeIdx ++ ) {
			CFBamDelDepBuff[] subList;
			classCode = classCodeList.get( classCodeIdx );
			if( classCode.equals( "a817" ) ) {
				subList = readAllBuff( Authorization );
			}
			else if( classCode.equals( "a818" ) ) {
				subList = schema.getTableDelSubDep1().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a819" ) ) {
				subList = schema.getTableDelSubDep2().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a81a" ) ) {
				subList = schema.getTableDelSubDep3().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a81b" ) ) {
				subList = schema.getTableDelTopDep().readAllBuff( Authorization );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
			for( int idxSubList = 0; idxSubList < subList.length; idxSubList ++ ) {
				resultList.add( subList[ idxSubList ] );
			}
		}
		int idx = 0;
		buffArray = new CFBamDelDepBuff[ resultList.size() ];
		Iterator<CFBamDelDepBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			buffArray[idx++] = iter.next();
		}
		return( buffArray );
	}

	public CFBamDelDepBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlDelDepTable.readDerivedByIdIdx() ";
		CFBamDelDepBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep_cc_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByIdIdx == null ) {
				stmtReadClassCodeByIdIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadClassCodeByIdIdx.setLong( argIdx++, TenantId );
			stmtReadClassCodeByIdIdx.setLong( argIdx++, Id );
			resultSet = stmtReadClassCodeByIdIdx.executeQuery();
			if( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
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
		if( classCode.equals( "a817" ) ) {
			buff = readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a818" ) ) {
			buff = schema.getTableDelSubDep1().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a819" ) ) {
			buff = schema.getTableDelSubDep2().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a81a" ) ) {
			buff = schema.getTableDelSubDep3().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a81b" ) ) {
			buff = schema.getTableDelTopDep().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Did not expect ClassCode \"" + classCode + "\"" );
		}
		return( buff );
	}

	public CFBamDelDepBuff[] readDerivedByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByTenantIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ArrayList<String> classCodeList = new ArrayList<String>();
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep_cc_by_tenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByTenantIdx == null ) {
				stmtReadClassCodeByTenantIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadClassCodeByTenantIdx.setLong( argIdx++, TenantId );
			resultSet = stmtReadClassCodeByTenantIdx.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
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
		List<CFBamDelDepBuff> resultList = new LinkedList<CFBamDelDepBuff>();
		ListIterator<String> classCodeIter = classCodeList.listIterator();
		while( classCodeIter.hasNext() ) {
			classCode = classCodeIter.next();
			if( classCode.equals( "a817" ) ) {
				CFBamDelDepBuff[] subList
					= readBuffByTenantIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a818" ) ) {
				CFBamDelSubDep1Buff[] subList
					= schema.getTableDelSubDep1().readBuffByTenantIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a819" ) ) {
				CFBamDelSubDep2Buff[] subList
					= schema.getTableDelSubDep2().readBuffByTenantIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81a" ) ) {
				CFBamDelSubDep3Buff[] subList
					= schema.getTableDelSubDep3().readBuffByTenantIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81b" ) ) {
				CFBamDelTopDepBuff[] subList
					= schema.getTableDelTopDep().readBuffByTenantIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
		}
		int idx = 0;
		CFBamDelDepBuff[] retBuff = new CFBamDelDepBuff[ resultList.size() ];
		Iterator<CFBamDelDepBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			retBuff[idx++] = iter.next();
		}
		return( retBuff );

	}

	public CFBamDelDepBuff[] readDerivedByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readDerivedByDefSchemaIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ArrayList<String> classCodeList = new ArrayList<String>();
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep_cc_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByDefSchemaIdx == null ) {
				stmtReadClassCodeByDefSchemaIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( DefSchemaTenantId != null ) {
				stmtReadClassCodeByDefSchemaIdx.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtReadClassCodeByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtReadClassCodeByDefSchemaIdx.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtReadClassCodeByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadClassCodeByDefSchemaIdx.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
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
		List<CFBamDelDepBuff> resultList = new LinkedList<CFBamDelDepBuff>();
		ListIterator<String> classCodeIter = classCodeList.listIterator();
		while( classCodeIter.hasNext() ) {
			classCode = classCodeIter.next();
			if( classCode.equals( "a817" ) ) {
				CFBamDelDepBuff[] subList
					= readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a818" ) ) {
				CFBamDelSubDep1Buff[] subList
					= schema.getTableDelSubDep1().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a819" ) ) {
				CFBamDelSubDep2Buff[] subList
					= schema.getTableDelSubDep2().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81a" ) ) {
				CFBamDelSubDep3Buff[] subList
					= schema.getTableDelSubDep3().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81b" ) ) {
				CFBamDelTopDepBuff[] subList
					= schema.getTableDelTopDep().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
		}
		int idx = 0;
		CFBamDelDepBuff[] retBuff = new CFBamDelDepBuff[ resultList.size() ];
		Iterator<CFBamDelDepBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			retBuff[idx++] = iter.next();
		}
		return( retBuff );

	}

	public CFBamDelDepBuff[] readDerivedByDelDepIdx( CFSecAuthorization Authorization,
		long RelationTenantId,
		long RelationId )
	{
		final String S_ProcName = "readDerivedByDelDepIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ArrayList<String> classCodeList = new ArrayList<String>();
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep_cc_by_deldepidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByDelDepIdx == null ) {
				stmtReadClassCodeByDelDepIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByDelDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByDelDepIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByDelDepIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByDelDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByDelDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadClassCodeByDelDepIdx.setLong( argIdx++, RelationTenantId );
			stmtReadClassCodeByDelDepIdx.setLong( argIdx++, RelationId );
			resultSet = stmtReadClassCodeByDelDepIdx.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
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
		List<CFBamDelDepBuff> resultList = new LinkedList<CFBamDelDepBuff>();
		ListIterator<String> classCodeIter = classCodeList.listIterator();
		while( classCodeIter.hasNext() ) {
			classCode = classCodeIter.next();
			if( classCode.equals( "a817" ) ) {
				CFBamDelDepBuff[] subList
					= readBuffByDelDepIdx( Authorization,
						RelationTenantId,
						RelationId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a818" ) ) {
				CFBamDelSubDep1Buff[] subList
					= schema.getTableDelSubDep1().readBuffByDelDepIdx( Authorization,
						RelationTenantId,
						RelationId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a819" ) ) {
				CFBamDelSubDep2Buff[] subList
					= schema.getTableDelSubDep2().readBuffByDelDepIdx( Authorization,
						RelationTenantId,
						RelationId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81a" ) ) {
				CFBamDelSubDep3Buff[] subList
					= schema.getTableDelSubDep3().readBuffByDelDepIdx( Authorization,
						RelationTenantId,
						RelationId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81b" ) ) {
				CFBamDelTopDepBuff[] subList
					= schema.getTableDelTopDep().readBuffByDelDepIdx( Authorization,
						RelationTenantId,
						RelationId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
		}
		int idx = 0;
		CFBamDelDepBuff[] retBuff = new CFBamDelDepBuff[ resultList.size() ];
		Iterator<CFBamDelDepBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			retBuff[idx++] = iter.next();
		}
		return( retBuff );

	}

	public CFBamDelDepBuff readBuff( CFSecAuthorization Authorization,
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep( ?, ?, ?, ?, ?" + ", "
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
				CFBamDelDepBuff buff = unpackDelDepResultSetToBuff( resultSet );
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

	public CFBamDelDepBuff lockBuff( CFSecAuthorization Authorization,
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_del_dep( ?, ?, ?, ?, ?" + ", "
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
				CFBamDelDepBuff buff = unpackDelDepResultSetToBuff( resultSet );
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

	public CFBamDelDepBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep_all( ?, ?, ?, ?, ? )";
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
			List<CFBamDelDepBuff> buffList = new LinkedList<CFBamDelDepBuff>();
			while( resultSet.next() ) {
				CFBamDelDepBuff buff = unpackDelDepResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamDelDepBuff[] retBuff = new CFBamDelDepBuff[ buffList.size() ];
			Iterator<CFBamDelDepBuff> iter = buffList.iterator();
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

	public CFBamDelDepBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep_by_ididx( ?, ?, ?, ?, ?" + ", "
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
				CFBamDelDepBuff buff = unpackDelDepResultSetToBuff( resultSet );
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

	public CFBamDelDepBuff[] readBuffByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep_by_tenantidx( ?, ?, ?, ?, ?" + ", "
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
			List<CFBamDelDepBuff> buffList = new LinkedList<CFBamDelDepBuff>();
			while( resultSet.next() ) {
				CFBamDelDepBuff buff = unpackDelDepResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamDelDepBuff[] retBuff = new CFBamDelDepBuff[ buffList.size() ];
			Iterator<CFBamDelDepBuff> iter = buffList.iterator();
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

	public CFBamDelDepBuff[] readBuffByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readBuffByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
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
			List<CFBamDelDepBuff> buffList = new LinkedList<CFBamDelDepBuff>();
			while( resultSet.next() ) {
				CFBamDelDepBuff buff = unpackDelDepResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamDelDepBuff[] retBuff = new CFBamDelDepBuff[ buffList.size() ];
			Iterator<CFBamDelDepBuff> iter = buffList.iterator();
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

	public CFBamDelDepBuff[] readBuffByDelDepIdx( CFSecAuthorization Authorization,
		long RelationTenantId,
		long RelationId )
	{
		final String S_ProcName = "readBuffByDelDepIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_del_dep_by_deldepidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByDelDepIdx == null ) {
				stmtReadBuffByDelDepIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByDelDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByDelDepIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByDelDepIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByDelDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByDelDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByDelDepIdx.setLong( argIdx++, RelationTenantId );
			stmtReadBuffByDelDepIdx.setLong( argIdx++, RelationId );
			resultSet = stmtReadBuffByDelDepIdx.executeQuery();
			List<CFBamDelDepBuff> buffList = new LinkedList<CFBamDelDepBuff>();
			while( resultSet.next() ) {
				CFBamDelDepBuff buff = unpackDelDepResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamDelDepBuff[] retBuff = new CFBamDelDepBuff[ buffList.size() ];
			Iterator<CFBamDelDepBuff> iter = buffList.iterator();
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

	public void updateDelDep( CFSecAuthorization Authorization,
		CFBamDelDepBuff Buff )
	{
		final String S_ProcName = "updateDelDep";
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			long RelationTenantId = Buff.getRequiredRelationTenantId();
			long RelationId = Buff.getRequiredRelationId();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_del_dep( ?, ?, ?, ?, ?, ?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
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
			stmtUpdateByPKey.setLong( argIdx++, RelationTenantId );
			stmtUpdateByPKey.setLong( argIdx++, RelationId );
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamDelDepBuff updatedBuff = unpackDelDepResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setOptionalDefSchemaTenantId( updatedBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( updatedBuff.getOptionalDefSchemaId() );
				Buff.setRequiredRelationTenantId( updatedBuff.getRequiredRelationTenantId() );
				Buff.setRequiredRelationId( updatedBuff.getRequiredRelationId() );
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

	public void deleteDelDep( CFSecAuthorization Authorization,
		CFBamDelDepBuff Buff )
	{
		final String S_ProcName = "deleteDelDep";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_del_dep( ?, ?, ?, ?, ?" + ", "
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

	public void deleteDelDepByIdIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argId )
	{
		final String S_ProcName = "deleteDelDepByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_del_dep_by_ididx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteDelDepByIdIdx( CFSecAuthorization Authorization,
		CFBamScopePKey argKey )
	{
		deleteDelDepByIdIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredId() );
	}

	public void deleteDelDepByTenantIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteDelDepByTenantIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_del_dep_by_tenantidx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteDelDepByTenantIdx( CFSecAuthorization Authorization,
		CFBamScopeByTenantIdxKey argKey )
	{
		deleteDelDepByTenantIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteDelDepByDefSchemaIdx( CFSecAuthorization Authorization,
		Long argDefSchemaTenantId,
		Long argDefSchemaId )
	{
		final String S_ProcName = "deleteDelDepByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_del_dep_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteDelDepByDefSchemaIdx( CFSecAuthorization Authorization,
		CFBamDelDepByDefSchemaIdxKey argKey )
	{
		deleteDelDepByDefSchemaIdx( Authorization,
			argKey.getOptionalDefSchemaTenantId(),
			argKey.getOptionalDefSchemaId() );
	}

	public void deleteDelDepByDelDepIdx( CFSecAuthorization Authorization,
		long argRelationTenantId,
		long argRelationId )
	{
		final String S_ProcName = "deleteDelDepByDelDepIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_del_dep_by_deldepidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByDelDepIdx == null ) {
					stmtDeleteByDelDepIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByDelDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByDelDepIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByDelDepIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByDelDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByDelDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByDelDepIdx.setLong( argIdx++, argRelationTenantId );
				stmtDeleteByDelDepIdx.setLong( argIdx++, argRelationId );
				resultSet = stmtDeleteByDelDepIdx.executeQuery();
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

	public void deleteDelDepByDelDepIdx( CFSecAuthorization Authorization,
		CFBamDelDepByDelDepIdxKey argKey )
	{
		deleteDelDepByDelDepIdx( Authorization,
			argKey.getRequiredRelationTenantId(),
			argKey.getRequiredRelationId() );
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
		S_sqlSelectDelDepDistinctClassCode = null;
		S_sqlSelectDelDepBuff = null;

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
		if( stmtDeleteByDefSchemaIdx != null ) {
			try {
				stmtDeleteByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByDefSchemaIdx = null;
		}
		if( stmtDeleteByDelDepIdx != null ) {
			try {
				stmtDeleteByDelDepIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByDelDepIdx = null;
		}
		if( stmtReadAllBuff != null ) {
			try {
				stmtReadAllBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtReadAllBuff = null;
		}
		if( stmtReadDerivedClassCode != null ) {
			try {
				stmtReadDerivedClassCode.close();
			}
			catch( SQLException e ) {
			}
			stmtReadDerivedClassCode = null;
		}
		if( stmtLockDerivedClassCode != null ) {
			try {
				stmtLockDerivedClassCode.close();
			}
			catch( SQLException e ) {
			}
			stmtLockDerivedClassCode = null;
		}
		if( stmtReadAllClassCode != null ) {
			try {
				stmtReadAllClassCode.close();
			}
			catch( SQLException e ) {
			}
			stmtReadAllClassCode = null;
		}
		if( stmtReadClassCodeByIdIdx != null ) {
			try {
				stmtReadClassCodeByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByIdIdx = null;
		}
		if( stmtReadBuffByIdIdx != null ) {
			try {
				stmtReadBuffByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByIdIdx = null;
		}
		if( stmtReadClassCodeByTenantIdx != null ) {
			try {
				stmtReadClassCodeByTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByTenantIdx = null;
		}
		if( stmtReadBuffByTenantIdx != null ) {
			try {
				stmtReadBuffByTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByTenantIdx = null;
		}
		if( stmtReadClassCodeByDefSchemaIdx != null ) {
			try {
				stmtReadClassCodeByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByDefSchemaIdx = null;
		}
		if( stmtReadBuffByDefSchemaIdx != null ) {
			try {
				stmtReadBuffByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByDefSchemaIdx = null;
		}
		if( stmtReadClassCodeByDelDepIdx != null ) {
			try {
				stmtReadClassCodeByDelDepIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByDelDepIdx = null;
		}
		if( stmtReadBuffByDelDepIdx != null ) {
			try {
				stmtReadBuffByDelDepIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByDelDepIdx = null;
		}
	}
}
