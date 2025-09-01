// Description: Java 11 PostgreSQL Jdbc DbIO implementation for PopSubDep3.

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
 *	CFBamPgSqlPopSubDep3Table PostgreSQL Jdbc DbIO implementation
 *	for PopSubDep3.
 */
public class CFBamPgSqlPopSubDep3Table
	implements ICFBamPopSubDep3Table
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
	protected PreparedStatement stmtReadBuffByRelationIdx = null;
	protected PreparedStatement stmtReadBuffByDefSchemaIdx = null;
	protected PreparedStatement stmtReadBuffByPopSubDep2Idx = null;
	protected PreparedStatement stmtReadBuffByUNameIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByTenantIdx = null;
	protected PreparedStatement stmtDeleteByRelationIdx = null;
	protected PreparedStatement stmtDeleteByDefSchemaIdx = null;
	protected PreparedStatement stmtDeleteByPopSubDep2Idx = null;
	protected PreparedStatement stmtDeleteByUNameIdx = null;

	public CFBamPgSqlPopSubDep3Table( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createPopSubDep3( CFSecAuthorization Authorization,
		CFBamPopSubDep3Buff Buff )
	{
		final String S_ProcName = "createPopSubDep3";
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
			long PopSubDep2TenantId = Buff.getRequiredPopSubDep2TenantId();
			long PopSubDep2Id = Buff.getRequiredPopSubDep2Id();
			String Name = Buff.getRequiredName();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_popsubdep3( ?, ?, ?, ?, ?, ?" + ", "
				+		"?" + ", "
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
			stmtCreateByPKey.setLong( argIdx++, PopSubDep2TenantId );
			stmtCreateByPKey.setLong( argIdx++, PopSubDep2Id );
			stmtCreateByPKey.setString( argIdx++, Name );
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamPopSubDep3Buff createdBuff = unpackPopSubDep3ResultSetToBuff( resultSet );
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
				Buff.setRequiredPopSubDep2TenantId( createdBuff.getRequiredPopSubDep2TenantId() );
				Buff.setRequiredPopSubDep2Id( createdBuff.getRequiredPopSubDep2Id() );
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

	public String S_sqlSelectPopSubDep3DistinctClassCode = null;

	public String getSqlSelectPopSubDep3DistinctClassCode() {
		if( S_sqlSelectPopSubDep3DistinctClassCode == null ) {
			S_sqlSelectPopSubDep3DistinctClassCode =
					"SELECT "
					+		"DISTINCT a801.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".pop_dep a830 ON "
					+		"a830.TenantId = a801.TenantId "
					+		"AND a830.Id = a801.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".popsubdep3 a833 ON "
					+		"a833.TenantId = a801.TenantId "
					+		"AND a833.Id = a801.Id ";
		}
		return( S_sqlSelectPopSubDep3DistinctClassCode );
	}

	public String S_sqlSelectPopSubDep3Buff = null;

	public String getSqlSelectPopSubDep3Buff() {
		if( S_sqlSelectPopSubDep3Buff == null ) {
			S_sqlSelectPopSubDep3Buff =
					"SELECT "
					+		"a801.ClassCode, "
					+		"a801.TenantId, "
					+		"a801.Id, "
					+		"a830.defschtentid, "
					+		"a830.defschid, "
					+		"a830.RelationTenantId, "
					+		"a830.RelationId, "
					+		"a833.PopSubDep2TenantId, "
					+		"a833.contpopdep2id, "
					+		"a833.safe_name, "
					+		"a801.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".pop_dep a830 ON "
					+		"a830.TenantId = a801.TenantId "
					+		"AND a830.Id = a801.Id "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".popsubdep3 a833 ON "
					+		"a833.TenantId = a801.TenantId "
					+		"AND a833.Id = a801.Id ";
		}
		return( S_sqlSelectPopSubDep3Buff );
	}

	protected CFBamPopSubDep3Buff unpackPopSubDep3ResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackPopSubDep3ResultSetToBuff";
		int idxcol = 1;
		String classCode = resultSet.getString( idxcol );
		idxcol++;
		CFBamPopSubDep3Buff buff;
		if( classCode.equals( "a833" ) ) {
			buff = schema.getFactoryPopSubDep3().newBuff();
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
		buff.setRequiredPopSubDep2TenantId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredPopSubDep2Id( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredName( resultSet.getString( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFBamPopSubDep3Buff readDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamPopSubDep3Buff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamPopSubDep3Buff lockDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamPopSubDep3Buff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamPopSubDep3Buff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFBamPopSubDep3Buff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFBamPopSubDep3Buff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlPopSubDep3Table.readDerivedByIdIdx() ";
		CFBamPopSubDep3Buff buff;
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

	public CFBamPopSubDep3Buff[] readDerivedByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByTenantIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamPopSubDep3Buff[] buffList = readBuffByTenantIdx( Authorization,
				TenantId );
		return( buffList );

	}

	public CFBamPopSubDep3Buff[] readDerivedByRelationIdx( CFSecAuthorization Authorization,
		long RelationTenantId,
		long RelationId )
	{
		final String S_ProcName = "readDerivedByRelationIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamPopSubDep3Buff[] buffList = readBuffByRelationIdx( Authorization,
				RelationTenantId,
				RelationId );
		return( buffList );

	}

	public CFBamPopSubDep3Buff[] readDerivedByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readDerivedByDefSchemaIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamPopSubDep3Buff[] buffList = readBuffByDefSchemaIdx( Authorization,
				DefSchemaTenantId,
				DefSchemaId );
		return( buffList );

	}

	public CFBamPopSubDep3Buff[] readDerivedByPopSubDep2Idx( CFSecAuthorization Authorization,
		long PopSubDep2TenantId,
		long PopSubDep2Id )
	{
		final String S_ProcName = "readDerivedByPopSubDep2Idx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamPopSubDep3Buff[] buffList = readBuffByPopSubDep2Idx( Authorization,
				PopSubDep2TenantId,
				PopSubDep2Id );
		return( buffList );

	}

	public CFBamPopSubDep3Buff readDerivedByUNameIdx( CFSecAuthorization Authorization,
		long PopSubDep2TenantId,
		long PopSubDep2Id,
		String Name )
	{
		final String S_ProcName = "CFBamPgSqlPopSubDep3Table.readDerivedByUNameIdx() ";
		CFBamPopSubDep3Buff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUNameIdx( Authorization,
				PopSubDep2TenantId,
				PopSubDep2Id,
				Name );
		return( buff );
	}

	public CFBamPopSubDep3Buff readBuff( CFSecAuthorization Authorization,
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_popsubdep3( ?, ?, ?, ?, ?" + ", "
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
				CFBamPopSubDep3Buff buff = unpackPopSubDep3ResultSetToBuff( resultSet );
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

	public CFBamPopSubDep3Buff lockBuff( CFSecAuthorization Authorization,
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_popsubdep3( ?, ?, ?, ?, ?" + ", "
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
				CFBamPopSubDep3Buff buff = unpackPopSubDep3ResultSetToBuff( resultSet );
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

	public CFBamPopSubDep3Buff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_popsubdep3_all( ?, ?, ?, ?, ? )";
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
			List<CFBamPopSubDep3Buff> buffList = new LinkedList<CFBamPopSubDep3Buff>();
			while( resultSet.next() ) {
				CFBamPopSubDep3Buff buff = unpackPopSubDep3ResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamPopSubDep3Buff[] retBuff = new CFBamPopSubDep3Buff[ buffList.size() ];
			Iterator<CFBamPopSubDep3Buff> iter = buffList.iterator();
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

	public CFBamPopSubDep3Buff readBuffByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_popsubdep3_by_ididx( ?, ?, ?, ?, ?" + ", "
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
				CFBamPopSubDep3Buff buff = unpackPopSubDep3ResultSetToBuff( resultSet );
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

	public CFBamPopSubDep3Buff[] readBuffByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_popsubdep3_by_tenantidx( ?, ?, ?, ?, ?" + ", "
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
			List<CFBamPopSubDep3Buff> buffList = new LinkedList<CFBamPopSubDep3Buff>();
			while( resultSet.next() ) {
				CFBamPopSubDep3Buff buff = unpackPopSubDep3ResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamPopSubDep3Buff[] retBuff = new CFBamPopSubDep3Buff[ buffList.size() ];
			Iterator<CFBamPopSubDep3Buff> iter = buffList.iterator();
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

	public CFBamPopSubDep3Buff[] readBuffByRelationIdx( CFSecAuthorization Authorization,
		long RelationTenantId,
		long RelationId )
	{
		final String S_ProcName = "readBuffByRelationIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_popsubdep3_by_relationidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByRelationIdx == null ) {
				stmtReadBuffByRelationIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByRelationIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByRelationIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByRelationIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByRelationIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByRelationIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByRelationIdx.setLong( argIdx++, RelationTenantId );
			stmtReadBuffByRelationIdx.setLong( argIdx++, RelationId );
			resultSet = stmtReadBuffByRelationIdx.executeQuery();
			List<CFBamPopSubDep3Buff> buffList = new LinkedList<CFBamPopSubDep3Buff>();
			while( resultSet.next() ) {
				CFBamPopSubDep3Buff buff = unpackPopSubDep3ResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamPopSubDep3Buff[] retBuff = new CFBamPopSubDep3Buff[ buffList.size() ];
			Iterator<CFBamPopSubDep3Buff> iter = buffList.iterator();
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

	public CFBamPopSubDep3Buff[] readBuffByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readBuffByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_popsubdep3_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
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
			List<CFBamPopSubDep3Buff> buffList = new LinkedList<CFBamPopSubDep3Buff>();
			while( resultSet.next() ) {
				CFBamPopSubDep3Buff buff = unpackPopSubDep3ResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamPopSubDep3Buff[] retBuff = new CFBamPopSubDep3Buff[ buffList.size() ];
			Iterator<CFBamPopSubDep3Buff> iter = buffList.iterator();
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

	public CFBamPopSubDep3Buff[] readBuffByPopSubDep2Idx( CFSecAuthorization Authorization,
		long PopSubDep2TenantId,
		long PopSubDep2Id )
	{
		final String S_ProcName = "readBuffByPopSubDep2Idx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_popsubdep3_by_popsubdep2idx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByPopSubDep2Idx == null ) {
				stmtReadBuffByPopSubDep2Idx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByPopSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPopSubDep2Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByPopSubDep2Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByPopSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPopSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByPopSubDep2Idx.setLong( argIdx++, PopSubDep2TenantId );
			stmtReadBuffByPopSubDep2Idx.setLong( argIdx++, PopSubDep2Id );
			resultSet = stmtReadBuffByPopSubDep2Idx.executeQuery();
			List<CFBamPopSubDep3Buff> buffList = new LinkedList<CFBamPopSubDep3Buff>();
			while( resultSet.next() ) {
				CFBamPopSubDep3Buff buff = unpackPopSubDep3ResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamPopSubDep3Buff[] retBuff = new CFBamPopSubDep3Buff[ buffList.size() ];
			Iterator<CFBamPopSubDep3Buff> iter = buffList.iterator();
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

	public CFBamPopSubDep3Buff readBuffByUNameIdx( CFSecAuthorization Authorization,
		long PopSubDep2TenantId,
		long PopSubDep2Id,
		String Name )
	{
		final String S_ProcName = "readBuffByUNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_popsubdep3_by_unameidx( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByUNameIdx.setLong( argIdx++, PopSubDep2TenantId );
			stmtReadBuffByUNameIdx.setLong( argIdx++, PopSubDep2Id );
			stmtReadBuffByUNameIdx.setString( argIdx++, Name );
			resultSet = stmtReadBuffByUNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamPopSubDep3Buff buff = unpackPopSubDep3ResultSetToBuff( resultSet );
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

	public void updatePopSubDep3( CFSecAuthorization Authorization,
		CFBamPopSubDep3Buff Buff )
	{
		final String S_ProcName = "updatePopSubDep3";
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			long RelationTenantId = Buff.getRequiredRelationTenantId();
			long RelationId = Buff.getRequiredRelationId();
			long PopSubDep2TenantId = Buff.getRequiredPopSubDep2TenantId();
			long PopSubDep2Id = Buff.getRequiredPopSubDep2Id();
			String Name = Buff.getRequiredName();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_popsubdep3( ?, ?, ?, ?, ?, ?" + ", "
						+	"?" + ", "
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
			stmtUpdateByPKey.setLong( argIdx++, PopSubDep2TenantId );
			stmtUpdateByPKey.setLong( argIdx++, PopSubDep2Id );
			stmtUpdateByPKey.setString( argIdx++, Name );
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamPopSubDep3Buff updatedBuff = unpackPopSubDep3ResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setOptionalDefSchemaTenantId( updatedBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( updatedBuff.getOptionalDefSchemaId() );
				Buff.setRequiredRelationTenantId( updatedBuff.getRequiredRelationTenantId() );
				Buff.setRequiredRelationId( updatedBuff.getRequiredRelationId() );
				Buff.setRequiredPopSubDep2TenantId( updatedBuff.getRequiredPopSubDep2TenantId() );
				Buff.setRequiredPopSubDep2Id( updatedBuff.getRequiredPopSubDep2Id() );
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

	public void deletePopSubDep3( CFSecAuthorization Authorization,
		CFBamPopSubDep3Buff Buff )
	{
		final String S_ProcName = "deletePopSubDep3";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_popsubdep3( ?, ?, ?, ?, ?" + ", "
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

	public void deletePopSubDep3ByIdIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argId )
	{
		final String S_ProcName = "deletePopSubDep3ByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_popsubdep3_by_ididx( ?, ?, ?, ?, ?" + ", "
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

	public void deletePopSubDep3ByIdIdx( CFSecAuthorization Authorization,
		CFBamScopePKey argKey )
	{
		deletePopSubDep3ByIdIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredId() );
	}

	public void deletePopSubDep3ByTenantIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deletePopSubDep3ByTenantIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_popsubdep3_by_tenantidx( ?, ?, ?, ?, ?" + ", "
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

	public void deletePopSubDep3ByTenantIdx( CFSecAuthorization Authorization,
		CFBamScopeByTenantIdxKey argKey )
	{
		deletePopSubDep3ByTenantIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deletePopSubDep3ByRelationIdx( CFSecAuthorization Authorization,
		long argRelationTenantId,
		long argRelationId )
	{
		final String S_ProcName = "deletePopSubDep3ByRelationIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_popsubdep3_by_relationidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByRelationIdx == null ) {
					stmtDeleteByRelationIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByRelationIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByRelationIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByRelationIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByRelationIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByRelationIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByRelationIdx.setLong( argIdx++, argRelationTenantId );
				stmtDeleteByRelationIdx.setLong( argIdx++, argRelationId );
				resultSet = stmtDeleteByRelationIdx.executeQuery();
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

	public void deletePopSubDep3ByRelationIdx( CFSecAuthorization Authorization,
		CFBamPopDepByRelationIdxKey argKey )
	{
		deletePopSubDep3ByRelationIdx( Authorization,
			argKey.getRequiredRelationTenantId(),
			argKey.getRequiredRelationId() );
	}

	public void deletePopSubDep3ByDefSchemaIdx( CFSecAuthorization Authorization,
		Long argDefSchemaTenantId,
		Long argDefSchemaId )
	{
		final String S_ProcName = "deletePopSubDep3ByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_popsubdep3_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
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

	public void deletePopSubDep3ByDefSchemaIdx( CFSecAuthorization Authorization,
		CFBamPopDepByDefSchemaIdxKey argKey )
	{
		deletePopSubDep3ByDefSchemaIdx( Authorization,
			argKey.getOptionalDefSchemaTenantId(),
			argKey.getOptionalDefSchemaId() );
	}

	public void deletePopSubDep3ByPopSubDep2Idx( CFSecAuthorization Authorization,
		long argPopSubDep2TenantId,
		long argPopSubDep2Id )
	{
		final String S_ProcName = "deletePopSubDep3ByPopSubDep2Idx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_popsubdep3_by_popsubdep2idx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByPopSubDep2Idx == null ) {
					stmtDeleteByPopSubDep2Idx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByPopSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByPopSubDep2Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByPopSubDep2Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByPopSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByPopSubDep2Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByPopSubDep2Idx.setLong( argIdx++, argPopSubDep2TenantId );
				stmtDeleteByPopSubDep2Idx.setLong( argIdx++, argPopSubDep2Id );
				resultSet = stmtDeleteByPopSubDep2Idx.executeQuery();
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

	public void deletePopSubDep3ByPopSubDep2Idx( CFSecAuthorization Authorization,
		CFBamPopSubDep3ByPopSubDep2IdxKey argKey )
	{
		deletePopSubDep3ByPopSubDep2Idx( Authorization,
			argKey.getRequiredPopSubDep2TenantId(),
			argKey.getRequiredPopSubDep2Id() );
	}

	public void deletePopSubDep3ByUNameIdx( CFSecAuthorization Authorization,
		long argPopSubDep2TenantId,
		long argPopSubDep2Id,
		String argName )
	{
		final String S_ProcName = "deletePopSubDep3ByUNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_popsubdep3_by_unameidx( ?, ?, ?, ?, ?" + ", "
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
				stmtDeleteByUNameIdx.setLong( argIdx++, argPopSubDep2TenantId );
				stmtDeleteByUNameIdx.setLong( argIdx++, argPopSubDep2Id );
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

	public void deletePopSubDep3ByUNameIdx( CFSecAuthorization Authorization,
		CFBamPopSubDep3ByUNameIdxKey argKey )
	{
		deletePopSubDep3ByUNameIdx( Authorization,
			argKey.getRequiredPopSubDep2TenantId(),
			argKey.getRequiredPopSubDep2Id(),
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
		S_sqlSelectPopSubDep3DistinctClassCode = null;
		S_sqlSelectPopSubDep3Buff = null;

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
		if( stmtDeleteByRelationIdx != null ) {
			try {
				stmtDeleteByRelationIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByRelationIdx = null;
		}
		if( stmtDeleteByDefSchemaIdx != null ) {
			try {
				stmtDeleteByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByDefSchemaIdx = null;
		}
		if( stmtDeleteByPopSubDep2Idx != null ) {
			try {
				stmtDeleteByPopSubDep2Idx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByPopSubDep2Idx = null;
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
		if( stmtReadBuffByRelationIdx != null ) {
			try {
				stmtReadBuffByRelationIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByRelationIdx = null;
		}
		if( stmtReadBuffByDefSchemaIdx != null ) {
			try {
				stmtReadBuffByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByDefSchemaIdx = null;
		}
		if( stmtReadBuffByPopSubDep2Idx != null ) {
			try {
				stmtReadBuffByPopSubDep2Idx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByPopSubDep2Idx = null;
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
