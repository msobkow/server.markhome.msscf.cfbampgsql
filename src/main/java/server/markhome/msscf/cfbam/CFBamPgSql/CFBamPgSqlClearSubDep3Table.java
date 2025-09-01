// Description: Java 11 PostgreSQL Jdbc DbIO implementation for ClearSubDep3.

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
 *	CFBamPgSqlClearSubDep3Table PostgreSQL Jdbc DbIO implementation
 *	for ClearSubDep3.
 */
public class CFBamPgSqlClearSubDep3Table
	implements ICFBamClearSubDep3Table
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
	protected PreparedStatement stmtReadBuffByClearDepIdx = null;
	protected PreparedStatement stmtReadBuffByDefSchemaIdx = null;
	protected PreparedStatement stmtReadBuffByClearSubDep2Idx = null;
	protected PreparedStatement stmtReadBuffByUNameIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByTenantIdx = null;
	protected PreparedStatement stmtDeleteByClearDepIdx = null;
	protected PreparedStatement stmtDeleteByDefSchemaIdx = null;
	protected PreparedStatement stmtDeleteByClearSubDep2Idx = null;
	protected PreparedStatement stmtDeleteByUNameIdx = null;

	public CFBamPgSqlClearSubDep3Table( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createClearSubDep3( CFSecAuthorization Authorization,
		CFBamClearSubDep3Buff Buff )
	{
		final String S_ProcName = "createClearSubDep3";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long RelationId = Buff.getRequiredRelationId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			long ClearSubDep2TenantId = Buff.getRequiredClearSubDep2TenantId();
			long ClearSubDep2Id = Buff.getRequiredClearSubDep2Id();
			String Name = Buff.getRequiredName();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_clrsubdep3( ?, ?, ?, ?, ?, ?" + ", "
				+		"?" + ", "
				+		"?" + ", "
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
			stmtCreateByPKey.setLong( argIdx++, RelationId );
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
			stmtCreateByPKey.setLong( argIdx++, ClearSubDep2TenantId );
			stmtCreateByPKey.setLong( argIdx++, ClearSubDep2Id );
			stmtCreateByPKey.setString( argIdx++, Name );
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamClearSubDep3Buff createdBuff = unpackClearSubDep3ResultSetToBuff( resultSet );
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
				Buff.setRequiredRelationId( createdBuff.getRequiredRelationId() );
				Buff.setOptionalDefSchemaTenantId( createdBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( createdBuff.getOptionalDefSchemaId() );
				Buff.setRequiredClearSubDep2TenantId( createdBuff.getRequiredClearSubDep2TenantId() );
				Buff.setRequiredClearSubDep2Id( createdBuff.getRequiredClearSubDep2Id() );
				Buff.setRequiredName( createdBuff.getRequiredName() );
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

	public String S_sqlSelectClearSubDep3DistinctClassCode = null;

	public String getSqlSelectClearSubDep3DistinctClassCode() {
		if( S_sqlSelectClearSubDep3DistinctClassCode == null ) {
			S_sqlSelectClearSubDep3DistinctClassCode =
					"SELECT "
					+		"DISTINCT a801.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".clr_dep a810 ON "
					+		"a810.TenantId = a801.TenantId "
					+		"AND a810.Id = a801.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".clrsubdep3 a813 ON "
					+		"a813.TenantId = a801.TenantId "
					+		"AND a813.Id = a801.Id ";
		}
		return( S_sqlSelectClearSubDep3DistinctClassCode );
	}

	public String S_sqlSelectClearSubDep3Buff = null;

	public String getSqlSelectClearSubDep3Buff() {
		if( S_sqlSelectClearSubDep3Buff == null ) {
			S_sqlSelectClearSubDep3Buff =
					"SELECT "
					+		"a801.ClassCode, "
					+		"a801.TenantId, "
					+		"a801.Id, "
					+		"a810.RelationId, "
					+		"a810.defschtentid, "
					+		"a810.defschid, "
					+		"a813.contclrdep2tid, "
					+		"a813.contclrdep2id, "
					+		"a813.safe_name, "
					+		"a801.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".clr_dep a810 ON "
					+		"a810.TenantId = a801.TenantId "
					+		"AND a810.Id = a801.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".clrsubdep3 a813 ON "
					+		"a813.TenantId = a801.TenantId "
					+		"AND a813.Id = a801.Id ";
		}
		return( S_sqlSelectClearSubDep3Buff );
	}

	protected CFBamClearSubDep3Buff unpackClearSubDep3ResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackClearSubDep3ResultSetToBuff";
		int idxcol = 1;
		String classCode = resultSet.getString( idxcol );
		idxcol++;
		CFBamClearSubDep3Buff buff;
		if( classCode.equals( "a813" ) ) {
			buff = schema.getFactoryClearSubDep3().newBuff();
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
		buff.setRequiredRelationId( resultSet.getLong( idxcol ) );
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
		buff.setRequiredClearSubDep2TenantId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredClearSubDep2Id( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredName( resultSet.getString( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFBamClearSubDep3Buff readDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamClearSubDep3Buff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamClearSubDep3Buff lockDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamClearSubDep3Buff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamClearSubDep3Buff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFBamClearSubDep3Buff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFBamClearSubDep3Buff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlClearSubDep3Table.readDerivedByIdIdx() ";
		CFBamClearSubDep3Buff buff;
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

	public CFBamClearSubDep3Buff[] readDerivedByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByTenantIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamClearSubDep3Buff[] buffList = readBuffByTenantIdx( Authorization,
				TenantId );
		return( buffList );

	}

	public CFBamClearSubDep3Buff[] readDerivedByClearDepIdx( CFSecAuthorization Authorization,
		long TenantId,
		long RelationId )
	{
		final String S_ProcName = "readDerivedByClearDepIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamClearSubDep3Buff[] buffList = readBuffByClearDepIdx( Authorization,
				TenantId,
				RelationId );
		return( buffList );

	}

	public CFBamClearSubDep3Buff[] readDerivedByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readDerivedByDefSchemaIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamClearSubDep3Buff[] buffList = readBuffByDefSchemaIdx( Authorization,
				DefSchemaTenantId,
				DefSchemaId );
		return( buffList );

	}

	public CFBamClearSubDep3Buff[] readDerivedByClearSubDep2Idx( CFSecAuthorization Authorization,
		long ClearSubDep2TenantId,
		long ClearSubDep2Id )
	{
		final String S_ProcName = "readDerivedByClearSubDep2Idx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamClearSubDep3Buff[] buffList = readBuffByClearSubDep2Idx( Authorization,
				ClearSubDep2TenantId,
				ClearSubDep2Id );
		return( buffList );

	}

	public CFBamClearSubDep3Buff readDerivedByUNameIdx( CFSecAuthorization Authorization,
		long ClearSubDep2TenantId,
		long ClearSubDep2Id,
		String Name )
	{
		final String S_ProcName = "CFBamPgSqlClearSubDep3Table.readDerivedByUNameIdx() ";
		CFBamClearSubDep3Buff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUNameIdx( Authorization,
				ClearSubDep2TenantId,
				ClearSubDep2Id,
				Name );
		return( buff );
	}

	public CFBamClearSubDep3Buff readBuff( CFSecAuthorization Authorization,
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clrsubdep3( ?, ?, ?, ?, ?" + ", "
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
				CFBamClearSubDep3Buff buff = unpackClearSubDep3ResultSetToBuff( resultSet );
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

	public CFBamClearSubDep3Buff lockBuff( CFSecAuthorization Authorization,
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_clrsubdep3( ?, ?, ?, ?, ?" + ", "
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
				CFBamClearSubDep3Buff buff = unpackClearSubDep3ResultSetToBuff( resultSet );
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

	public CFBamClearSubDep3Buff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clrsubdep3_all( ?, ?, ?, ?, ? )";
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
			List<CFBamClearSubDep3Buff> buffList = new LinkedList<CFBamClearSubDep3Buff>();
			while( resultSet.next() ) {
				CFBamClearSubDep3Buff buff = unpackClearSubDep3ResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamClearSubDep3Buff[] retBuff = new CFBamClearSubDep3Buff[ buffList.size() ];
			Iterator<CFBamClearSubDep3Buff> iter = buffList.iterator();
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

	public CFBamClearSubDep3Buff readBuffByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clrsubdep3_by_ididx( ?, ?, ?, ?, ?" + ", "
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
				CFBamClearSubDep3Buff buff = unpackClearSubDep3ResultSetToBuff( resultSet );
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

	public CFBamClearSubDep3Buff[] readBuffByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clrsubdep3_by_tenantidx( ?, ?, ?, ?, ?" + ", "
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
			List<CFBamClearSubDep3Buff> buffList = new LinkedList<CFBamClearSubDep3Buff>();
			while( resultSet.next() ) {
				CFBamClearSubDep3Buff buff = unpackClearSubDep3ResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamClearSubDep3Buff[] retBuff = new CFBamClearSubDep3Buff[ buffList.size() ];
			Iterator<CFBamClearSubDep3Buff> iter = buffList.iterator();
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

	public CFBamClearSubDep3Buff[] readBuffByClearDepIdx( CFSecAuthorization Authorization,
		long TenantId,
		long RelationId )
	{
		final String S_ProcName = "readBuffByClearDepIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clrsubdep3_by_cleardepidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByClearDepIdx == null ) {
				stmtReadBuffByClearDepIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByClearDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByClearDepIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByClearDepIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByClearDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByClearDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByClearDepIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByClearDepIdx.setLong( argIdx++, RelationId );
			resultSet = stmtReadBuffByClearDepIdx.executeQuery();
			List<CFBamClearSubDep3Buff> buffList = new LinkedList<CFBamClearSubDep3Buff>();
			while( resultSet.next() ) {
				CFBamClearSubDep3Buff buff = unpackClearSubDep3ResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamClearSubDep3Buff[] retBuff = new CFBamClearSubDep3Buff[ buffList.size() ];
			Iterator<CFBamClearSubDep3Buff> iter = buffList.iterator();
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

	public CFBamClearSubDep3Buff[] readBuffByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readBuffByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clrsubdep3_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
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
			List<CFBamClearSubDep3Buff> buffList = new LinkedList<CFBamClearSubDep3Buff>();
			while( resultSet.next() ) {
				CFBamClearSubDep3Buff buff = unpackClearSubDep3ResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamClearSubDep3Buff[] retBuff = new CFBamClearSubDep3Buff[ buffList.size() ];
			Iterator<CFBamClearSubDep3Buff> iter = buffList.iterator();
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

	public CFBamClearSubDep3Buff[] readBuffByClearSubDep2Idx( CFSecAuthorization Authorization,
		long ClearSubDep2TenantId,
		long ClearSubDep2Id )
	{
		final String S_ProcName = "readBuffByClearSubDep2Idx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clrsubdep3_by_clearsubdep2idx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByClearSubDep2Idx == null ) {
				stmtReadBuffByClearSubDep2Idx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByClearSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByClearSubDep2Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByClearSubDep2Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByClearSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByClearSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByClearSubDep2Idx.setLong( argIdx++, ClearSubDep2TenantId );
			stmtReadBuffByClearSubDep2Idx.setLong( argIdx++, ClearSubDep2Id );
			resultSet = stmtReadBuffByClearSubDep2Idx.executeQuery();
			List<CFBamClearSubDep3Buff> buffList = new LinkedList<CFBamClearSubDep3Buff>();
			while( resultSet.next() ) {
				CFBamClearSubDep3Buff buff = unpackClearSubDep3ResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamClearSubDep3Buff[] retBuff = new CFBamClearSubDep3Buff[ buffList.size() ];
			Iterator<CFBamClearSubDep3Buff> iter = buffList.iterator();
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

	public CFBamClearSubDep3Buff readBuffByUNameIdx( CFSecAuthorization Authorization,
		long ClearSubDep2TenantId,
		long ClearSubDep2Id,
		String Name )
	{
		final String S_ProcName = "readBuffByUNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_clrsubdep3_by_unameidx( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByUNameIdx.setLong( argIdx++, ClearSubDep2TenantId );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ClearSubDep2Id );
			stmtReadBuffByUNameIdx.setString( argIdx++, Name );
			resultSet = stmtReadBuffByUNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamClearSubDep3Buff buff = unpackClearSubDep3ResultSetToBuff( resultSet );
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

	public void updateClearSubDep3( CFSecAuthorization Authorization,
		CFBamClearSubDep3Buff Buff )
	{
		final String S_ProcName = "updateClearSubDep3";
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();
			long RelationId = Buff.getRequiredRelationId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			long ClearSubDep2TenantId = Buff.getRequiredClearSubDep2TenantId();
			long ClearSubDep2Id = Buff.getRequiredClearSubDep2Id();
			String Name = Buff.getRequiredName();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_clrsubdep3( ?, ?, ?, ?, ?, ?" + ", "
						+	"?" + ", "
						+	"?" + ", "
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
			stmtUpdateByPKey.setLong( argIdx++, RelationId );
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
			stmtUpdateByPKey.setLong( argIdx++, ClearSubDep2TenantId );
			stmtUpdateByPKey.setLong( argIdx++, ClearSubDep2Id );
			stmtUpdateByPKey.setString( argIdx++, Name );
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamClearSubDep3Buff updatedBuff = unpackClearSubDep3ResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredRelationId( updatedBuff.getRequiredRelationId() );
				Buff.setOptionalDefSchemaTenantId( updatedBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( updatedBuff.getOptionalDefSchemaId() );
				Buff.setRequiredClearSubDep2TenantId( updatedBuff.getRequiredClearSubDep2TenantId() );
				Buff.setRequiredClearSubDep2Id( updatedBuff.getRequiredClearSubDep2Id() );
				Buff.setRequiredName( updatedBuff.getRequiredName() );
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

	public void deleteClearSubDep3( CFSecAuthorization Authorization,
		CFBamClearSubDep3Buff Buff )
	{
		final String S_ProcName = "deleteClearSubDep3";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clrsubdep3( ?, ?, ?, ?, ?" + ", "
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

	public void deleteClearSubDep3ByIdIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argId )
	{
		final String S_ProcName = "deleteClearSubDep3ByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clrsubdep3_by_ididx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteClearSubDep3ByIdIdx( CFSecAuthorization Authorization,
		CFBamScopePKey argKey )
	{
		deleteClearSubDep3ByIdIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredId() );
	}

	public void deleteClearSubDep3ByTenantIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteClearSubDep3ByTenantIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clrsubdep3_by_tenantidx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteClearSubDep3ByTenantIdx( CFSecAuthorization Authorization,
		CFBamScopeByTenantIdxKey argKey )
	{
		deleteClearSubDep3ByTenantIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteClearSubDep3ByClearDepIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argRelationId )
	{
		final String S_ProcName = "deleteClearSubDep3ByClearDepIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clrsubdep3_by_cleardepidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByClearDepIdx == null ) {
					stmtDeleteByClearDepIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByClearDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByClearDepIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByClearDepIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByClearDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByClearDepIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByClearDepIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByClearDepIdx.setLong( argIdx++, argRelationId );
				resultSet = stmtDeleteByClearDepIdx.executeQuery();
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

	public void deleteClearSubDep3ByClearDepIdx( CFSecAuthorization Authorization,
		CFBamClearDepByClearDepIdxKey argKey )
	{
		deleteClearSubDep3ByClearDepIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredRelationId() );
	}

	public void deleteClearSubDep3ByDefSchemaIdx( CFSecAuthorization Authorization,
		Long argDefSchemaTenantId,
		Long argDefSchemaId )
	{
		final String S_ProcName = "deleteClearSubDep3ByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clrsubdep3_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteClearSubDep3ByDefSchemaIdx( CFSecAuthorization Authorization,
		CFBamClearDepByDefSchemaIdxKey argKey )
	{
		deleteClearSubDep3ByDefSchemaIdx( Authorization,
			argKey.getOptionalDefSchemaTenantId(),
			argKey.getOptionalDefSchemaId() );
	}

	public void deleteClearSubDep3ByClearSubDep2Idx( CFSecAuthorization Authorization,
		long argClearSubDep2TenantId,
		long argClearSubDep2Id )
	{
		final String S_ProcName = "deleteClearSubDep3ByClearSubDep2Idx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clrsubdep3_by_clearsubdep2idx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByClearSubDep2Idx == null ) {
					stmtDeleteByClearSubDep2Idx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByClearSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByClearSubDep2Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByClearSubDep2Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByClearSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByClearSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByClearSubDep2Idx.setLong( argIdx++, argClearSubDep2TenantId );
				stmtDeleteByClearSubDep2Idx.setLong( argIdx++, argClearSubDep2Id );
				resultSet = stmtDeleteByClearSubDep2Idx.executeQuery();
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

	public void deleteClearSubDep3ByClearSubDep2Idx( CFSecAuthorization Authorization,
		CFBamClearSubDep3ByClearSubDep2IdxKey argKey )
	{
		deleteClearSubDep3ByClearSubDep2Idx( Authorization,
			argKey.getRequiredClearSubDep2TenantId(),
			argKey.getRequiredClearSubDep2Id() );
	}

	public void deleteClearSubDep3ByUNameIdx( CFSecAuthorization Authorization,
		long argClearSubDep2TenantId,
		long argClearSubDep2Id,
		String argName )
	{
		final String S_ProcName = "deleteClearSubDep3ByUNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_clrsubdep3_by_unameidx( ?, ?, ?, ?, ?" + ", "
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
				stmtDeleteByUNameIdx.setLong( argIdx++, argClearSubDep2TenantId );
				stmtDeleteByUNameIdx.setLong( argIdx++, argClearSubDep2Id );
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

	public void deleteClearSubDep3ByUNameIdx( CFSecAuthorization Authorization,
		CFBamClearSubDep3ByUNameIdxKey argKey )
	{
		deleteClearSubDep3ByUNameIdx( Authorization,
			argKey.getRequiredClearSubDep2TenantId(),
			argKey.getRequiredClearSubDep2Id(),
			argKey.getRequiredName() );
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
		S_sqlSelectClearSubDep3DistinctClassCode = null;
		S_sqlSelectClearSubDep3Buff = null;

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
		if( stmtDeleteByClearDepIdx != null ) {
			try {
				stmtDeleteByClearDepIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByClearDepIdx = null;
		}
		if( stmtDeleteByDefSchemaIdx != null ) {
			try {
				stmtDeleteByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByDefSchemaIdx = null;
		}
		if( stmtDeleteByClearSubDep2Idx != null ) {
			try {
				stmtDeleteByClearSubDep2Idx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByClearSubDep2Idx = null;
		}
		if( stmtDeleteByUNameIdx != null ) {
			try {
				stmtDeleteByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUNameIdx = null;
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
		if( stmtReadBuffByClearDepIdx != null ) {
			try {
				stmtReadBuffByClearDepIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByClearDepIdx = null;
		}
		if( stmtReadBuffByDefSchemaIdx != null ) {
			try {
				stmtReadBuffByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByDefSchemaIdx = null;
		}
		if( stmtReadBuffByClearSubDep2Idx != null ) {
			try {
				stmtReadBuffByClearSubDep2Idx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByClearSubDep2Idx = null;
		}
		if( stmtReadBuffByUNameIdx != null ) {
			try {
				stmtReadBuffByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUNameIdx = null;
		}
	}
}
