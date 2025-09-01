// Description: Java 11 PostgreSQL Jdbc DbIO implementation for TSecGrpInc.

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
 *	CFBamPgSqlTSecGrpIncTable PostgreSQL Jdbc DbIO implementation
 *	for TSecGrpInc.
 */
public class CFBamPgSqlTSecGrpIncTable
	implements ICFBamTSecGrpIncTable
{
	private CFBamPgSqlSchema schema;
	protected PreparedStatement stmtReadBuffByPKey = null;
	protected PreparedStatement stmtReadBuffAll = null;
	protected PreparedStatement stmtPageBuffAll = null;
	protected PreparedStatement stmtLockBuffByPKey = null;
	protected PreparedStatement stmtCreateByPKey = null;
	protected PreparedStatement stmtUpdateByPKey = null;
	protected PreparedStatement stmtDeleteByPKey = null;
	protected PreparedStatement stmtReadAllBuff = null;
	protected PreparedStatement stmtReadBuffByIdIdx = null;
	protected PreparedStatement stmtReadBuffByTenantIdx = null;
	protected PreparedStatement stmtReadBuffByGroupIdx = null;
	protected PreparedStatement stmtReadBuffByIncludeIdx = null;
	protected PreparedStatement stmtReadBuffByUIncludeIdx = null;
	protected PreparedStatement stmtPageBuffByTenantIdx = null;
	protected PreparedStatement stmtPageBuffByGroupIdx = null;
	protected PreparedStatement stmtPageBuffByIncludeIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByTenantIdx = null;
	protected PreparedStatement stmtDeleteByGroupIdx = null;
	protected PreparedStatement stmtDeleteByIncludeIdx = null;
	protected PreparedStatement stmtDeleteByUIncludeIdx = null;

	public CFBamPgSqlTSecGrpIncTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createTSecGrpInc( CFSecAuthorization Authorization,
		CFSecTSecGrpIncBuff Buff )
	{
		final String S_ProcName = "createTSecGrpInc";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			long TenantId = Buff.getRequiredTenantId();
			int TSecGroupId = Buff.getRequiredTSecGroupId();
			int IncludeGroupId = Buff.getRequiredIncludeGroupId();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_tsecinc( ?, ?, ?, ?, ?, ?" + ", "
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
			stmtCreateByPKey.setString( argIdx++, "a017" );
			stmtCreateByPKey.setLong( argIdx++, TenantId );
			stmtCreateByPKey.setInt( argIdx++, TSecGroupId );
			stmtCreateByPKey.setInt( argIdx++, IncludeGroupId );
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecTSecGrpIncBuff createdBuff = unpackTSecGrpIncResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredTenantId( createdBuff.getRequiredTenantId() );
				Buff.setRequiredTSecGrpIncId( createdBuff.getRequiredTSecGrpIncId() );
				Buff.setRequiredTSecGroupId( createdBuff.getRequiredTSecGroupId() );
				Buff.setRequiredIncludeGroupId( createdBuff.getRequiredIncludeGroupId() );
				Buff.setRequiredRevision( createdBuff.getRequiredRevision() );
				Buff.setCreatedByUserId( createdBuff.getCreatedByUserId() );
				Buff.setCreatedAt( createdBuff.getCreatedAt() );
				Buff.setUpdatedByUserId( createdBuff.getUpdatedByUserId() );
				Buff.setUpdatedAt( createdBuff.getUpdatedAt() );
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

	public String S_sqlSelectTSecGrpIncDistinctClassCode = null;

	public String getSqlSelectTSecGrpIncDistinctClassCode() {
		if( S_sqlSelectTSecGrpIncDistinctClassCode == null ) {
			S_sqlSelectTSecGrpIncDistinctClassCode =
					"SELECT "
					+		"DISTINCT a017.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".TSecInc AS a017 ";
		}
		return( S_sqlSelectTSecGrpIncDistinctClassCode );
	}

	public String S_sqlSelectTSecGrpIncBuff = null;

	public String getSqlSelectTSecGrpIncBuff() {
		if( S_sqlSelectTSecGrpIncBuff == null ) {
			S_sqlSelectTSecGrpIncBuff =
					"SELECT "
					+		"a017.TenantId, "
					+		"a017.TSecGrpIncId, "
					+		"a017.TSecGrpId, "
					+		"a017.IncGrpId, "
					+		"a017.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".TSecInc AS a017 ";
		}
		return( S_sqlSelectTSecGrpIncBuff );
	}

	protected CFSecTSecGrpIncBuff unpackTSecGrpIncResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackTSecGrpIncResultSetToBuff";
		int idxcol = 1;
		CFSecTSecGrpIncBuff buff = schema.getFactoryTSecGrpInc().newBuff();
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
		buff.setRequiredTSecGrpIncId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredTSecGroupId( resultSet.getInt( idxcol ) );
		idxcol++;
		buff.setRequiredIncludeGroupId( resultSet.getInt( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFSecTSecGrpIncBuff readDerived( CFSecAuthorization Authorization,
		CFSecTSecGrpIncPKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecTSecGrpIncBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecTSecGrpIncBuff lockDerived( CFSecAuthorization Authorization,
		CFSecTSecGrpIncPKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecTSecGrpIncBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecTSecGrpIncBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFSecTSecGrpIncBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFSecTSecGrpIncBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TSecGrpIncId )
	{
		final String S_ProcName = "CFBamPgSqlTSecGrpIncTable.readDerivedByIdIdx() ";
		CFSecTSecGrpIncBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByIdIdx( Authorization,
				TenantId,
				TSecGrpIncId );
		return( buff );
	}

	public CFSecTSecGrpIncBuff[] readDerivedByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByTenantIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecTSecGrpIncBuff[] buffList = readBuffByTenantIdx( Authorization,
				TenantId );
		return( buffList );

	}

	public CFSecTSecGrpIncBuff[] readDerivedByGroupIdx( CFSecAuthorization Authorization,
		long TenantId,
		int TSecGroupId )
	{
		final String S_ProcName = "readDerivedByGroupIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecTSecGrpIncBuff[] buffList = readBuffByGroupIdx( Authorization,
				TenantId,
				TSecGroupId );
		return( buffList );

	}

	public CFSecTSecGrpIncBuff[] readDerivedByIncludeIdx( CFSecAuthorization Authorization,
		long TenantId,
		int IncludeGroupId )
	{
		final String S_ProcName = "readDerivedByIncludeIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecTSecGrpIncBuff[] buffList = readBuffByIncludeIdx( Authorization,
				TenantId,
				IncludeGroupId );
		return( buffList );

	}

	public CFSecTSecGrpIncBuff readDerivedByUIncludeIdx( CFSecAuthorization Authorization,
		long TenantId,
		int TSecGroupId,
		int IncludeGroupId )
	{
		final String S_ProcName = "CFBamPgSqlTSecGrpIncTable.readDerivedByUIncludeIdx() ";
		CFSecTSecGrpIncBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUIncludeIdx( Authorization,
				TenantId,
				TSecGroupId,
				IncludeGroupId );
		return( buff );
	}

	public CFSecTSecGrpIncBuff readBuff( CFSecAuthorization Authorization,
		CFSecTSecGrpIncPKey PKey )
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
			long TSecGrpIncId = PKey.getRequiredTSecGrpIncId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tsecinc( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByPKey.setLong( argIdx++, TSecGrpIncId );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
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

	public CFSecTSecGrpIncBuff lockBuff( CFSecAuthorization Authorization,
		CFSecTSecGrpIncPKey PKey )
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
			long TSecGrpIncId = PKey.getRequiredTSecGrpIncId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_tsecinc( ?, ?, ?, ?, ?" + ", "
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
			stmtLockBuffByPKey.setLong( argIdx++, TSecGrpIncId );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
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

	public CFSecTSecGrpIncBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tsecinc_all( ?, ?, ?, ?, ? )";
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
			List<CFSecTSecGrpIncBuff> buffList = new LinkedList<CFSecTSecGrpIncBuff>();
			while( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTSecGrpIncBuff[] retBuff = new CFSecTSecGrpIncBuff[ buffList.size() ];
			Iterator<CFSecTSecGrpIncBuff> iter = buffList.iterator();
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

	/**
	 *	Read a page of all the specific TSecGrpInc buffer instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific TSecGrpInc instances in the database accessible for the Authorization.
	 */
	public CFSecTSecGrpIncBuff[] pageAllBuff( CFSecAuthorization Authorization,
		Long priorTenantId,
		Long priorTSecGrpIncId )
	{
		final String S_ProcName = "pageAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_tsecinc_all( ?, ?, ?, ?, ?, ?, ? )";
			if( stmtPageBuffAll == null ) {
				stmtPageBuffAll = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffAll.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffAll.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( priorTenantId != null ) {
				stmtPageBuffAll.setLong( argIdx++, priorTenantId.longValue() );
			}
			else {
				stmtPageBuffAll.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorTSecGrpIncId != null ) {
				stmtPageBuffAll.setLong( argIdx++, priorTSecGrpIncId.longValue() );
			}
			else {
				stmtPageBuffAll.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffAll.executeQuery();
			List<CFSecTSecGrpIncBuff> buffList = new LinkedList<CFSecTSecGrpIncBuff>();
			while( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTSecGrpIncBuff[] retBuff = new CFSecTSecGrpIncBuff[ buffList.size() ];
			Iterator<CFSecTSecGrpIncBuff> iter = buffList.iterator();
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

	public CFSecTSecGrpIncBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TSecGrpIncId )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tsecinc_by_ididx( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByIdIdx.setLong( argIdx++, TSecGrpIncId );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
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

	public CFSecTSecGrpIncBuff[] readBuffByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tsecinc_by_tenantidx( ?, ?, ?, ?, ?" + ", "
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
			List<CFSecTSecGrpIncBuff> buffList = new LinkedList<CFSecTSecGrpIncBuff>();
			while( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTSecGrpIncBuff[] retBuff = new CFSecTSecGrpIncBuff[ buffList.size() ];
			Iterator<CFSecTSecGrpIncBuff> iter = buffList.iterator();
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

	public CFSecTSecGrpIncBuff[] readBuffByGroupIdx( CFSecAuthorization Authorization,
		long TenantId,
		int TSecGroupId )
	{
		final String S_ProcName = "readBuffByGroupIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tsecinc_by_groupidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByGroupIdx == null ) {
				stmtReadBuffByGroupIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByGroupIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByGroupIdx.setInt( argIdx++, TSecGroupId );
			resultSet = stmtReadBuffByGroupIdx.executeQuery();
			List<CFSecTSecGrpIncBuff> buffList = new LinkedList<CFSecTSecGrpIncBuff>();
			while( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTSecGrpIncBuff[] retBuff = new CFSecTSecGrpIncBuff[ buffList.size() ];
			Iterator<CFSecTSecGrpIncBuff> iter = buffList.iterator();
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

	public CFSecTSecGrpIncBuff[] readBuffByIncludeIdx( CFSecAuthorization Authorization,
		long TenantId,
		int IncludeGroupId )
	{
		final String S_ProcName = "readBuffByIncludeIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tsecinc_by_includeidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByIncludeIdx == null ) {
				stmtReadBuffByIncludeIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIncludeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByIncludeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByIncludeIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByIncludeIdx.setInt( argIdx++, IncludeGroupId );
			resultSet = stmtReadBuffByIncludeIdx.executeQuery();
			List<CFSecTSecGrpIncBuff> buffList = new LinkedList<CFSecTSecGrpIncBuff>();
			while( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTSecGrpIncBuff[] retBuff = new CFSecTSecGrpIncBuff[ buffList.size() ];
			Iterator<CFSecTSecGrpIncBuff> iter = buffList.iterator();
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

	public CFSecTSecGrpIncBuff readBuffByUIncludeIdx( CFSecAuthorization Authorization,
		long TenantId,
		int TSecGroupId,
		int IncludeGroupId )
	{
		final String S_ProcName = "readBuffByUIncludeIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tsecinc_by_uincludeidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUIncludeIdx == null ) {
				stmtReadBuffByUIncludeIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUIncludeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUIncludeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUIncludeIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByUIncludeIdx.setInt( argIdx++, TSecGroupId );
			stmtReadBuffByUIncludeIdx.setInt( argIdx++, IncludeGroupId );
			resultSet = stmtReadBuffByUIncludeIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
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
	 *	Read a page array of the specific TSecGrpInc buffer instances identified by the duplicate key TenantIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argTenantId	The TSecGrpInc key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecTSecGrpIncBuff[] pageBuffByTenantIdx( CFSecAuthorization Authorization,
		long TenantId,
		Long priorTenantId,
		Long priorTSecGrpIncId )
	{
		final String S_ProcName = "pageBuffByTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_tsecinc_by_tenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtPageBuffByTenantIdx == null ) {
				stmtPageBuffByTenantIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtPageBuffByTenantIdx.setLong( argIdx++, TenantId );
			if( priorTenantId != null ) {
				stmtPageBuffByTenantIdx.setLong( argIdx++, priorTenantId.longValue() );
			}
			else {
				stmtPageBuffByTenantIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorTSecGrpIncId != null ) {
				stmtPageBuffByTenantIdx.setLong( argIdx++, priorTSecGrpIncId.longValue() );
			}
			else {
				stmtPageBuffByTenantIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffByTenantIdx.executeQuery();
			List<CFSecTSecGrpIncBuff> buffList = new LinkedList<CFSecTSecGrpIncBuff>();
			while( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTSecGrpIncBuff[] retBuff = new CFSecTSecGrpIncBuff[ buffList.size() ];
			Iterator<CFSecTSecGrpIncBuff> iter = buffList.iterator();
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

	/**
	 *	Read a page array of the specific TSecGrpInc buffer instances identified by the duplicate key GroupIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argTenantId	The TSecGrpInc key attribute of the instance generating the id.
	 *
	 *	@param	argTSecGroupId	The TSecGrpInc key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecTSecGrpIncBuff[] pageBuffByGroupIdx( CFSecAuthorization Authorization,
		long TenantId,
		int TSecGroupId,
		Long priorTenantId,
		Long priorTSecGrpIncId )
	{
		final String S_ProcName = "pageBuffByGroupIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_tsecinc_by_groupidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtPageBuffByGroupIdx == null ) {
				stmtPageBuffByGroupIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtPageBuffByGroupIdx.setLong( argIdx++, TenantId );
			stmtPageBuffByGroupIdx.setInt( argIdx++, TSecGroupId );
			if( priorTenantId != null ) {
				stmtPageBuffByGroupIdx.setLong( argIdx++, priorTenantId.longValue() );
			}
			else {
				stmtPageBuffByGroupIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorTSecGrpIncId != null ) {
				stmtPageBuffByGroupIdx.setLong( argIdx++, priorTSecGrpIncId.longValue() );
			}
			else {
				stmtPageBuffByGroupIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffByGroupIdx.executeQuery();
			List<CFSecTSecGrpIncBuff> buffList = new LinkedList<CFSecTSecGrpIncBuff>();
			while( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTSecGrpIncBuff[] retBuff = new CFSecTSecGrpIncBuff[ buffList.size() ];
			Iterator<CFSecTSecGrpIncBuff> iter = buffList.iterator();
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

	/**
	 *	Read a page array of the specific TSecGrpInc buffer instances identified by the duplicate key IncludeIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argTenantId	The TSecGrpInc key attribute of the instance generating the id.
	 *
	 *	@param	argIncludeGroupId	The TSecGrpInc key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecTSecGrpIncBuff[] pageBuffByIncludeIdx( CFSecAuthorization Authorization,
		long TenantId,
		int IncludeGroupId,
		Long priorTenantId,
		Long priorTSecGrpIncId )
	{
		final String S_ProcName = "pageBuffByIncludeIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_tsecinc_by_includeidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtPageBuffByIncludeIdx == null ) {
				stmtPageBuffByIncludeIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffByIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByIncludeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffByIncludeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffByIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtPageBuffByIncludeIdx.setLong( argIdx++, TenantId );
			stmtPageBuffByIncludeIdx.setInt( argIdx++, IncludeGroupId );
			if( priorTenantId != null ) {
				stmtPageBuffByIncludeIdx.setLong( argIdx++, priorTenantId.longValue() );
			}
			else {
				stmtPageBuffByIncludeIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorTSecGrpIncId != null ) {
				stmtPageBuffByIncludeIdx.setLong( argIdx++, priorTSecGrpIncId.longValue() );
			}
			else {
				stmtPageBuffByIncludeIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffByIncludeIdx.executeQuery();
			List<CFSecTSecGrpIncBuff> buffList = new LinkedList<CFSecTSecGrpIncBuff>();
			while( resultSet.next() ) {
				CFSecTSecGrpIncBuff buff = unpackTSecGrpIncResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTSecGrpIncBuff[] retBuff = new CFSecTSecGrpIncBuff[ buffList.size() ];
			Iterator<CFSecTSecGrpIncBuff> iter = buffList.iterator();
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

	public void updateTSecGrpInc( CFSecAuthorization Authorization,
		CFSecTSecGrpIncBuff Buff )
	{
		final String S_ProcName = "updateTSecGrpInc";
		ResultSet resultSet = null;
		try {
			long TenantId = Buff.getRequiredTenantId();
			long TSecGrpIncId = Buff.getRequiredTSecGrpIncId();
			int TSecGroupId = Buff.getRequiredTSecGroupId();
			int IncludeGroupId = Buff.getRequiredIncludeGroupId();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_tsecinc( ?, ?, ?, ?, ?, ?" + ", "
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
			stmtUpdateByPKey.setString( argIdx++, "a017" );
			stmtUpdateByPKey.setLong( argIdx++, TenantId );
			stmtUpdateByPKey.setLong( argIdx++, TSecGrpIncId );
			stmtUpdateByPKey.setInt( argIdx++, TSecGroupId );
			stmtUpdateByPKey.setInt( argIdx++, IncludeGroupId );
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecTSecGrpIncBuff updatedBuff = unpackTSecGrpIncResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredTSecGroupId( updatedBuff.getRequiredTSecGroupId() );
				Buff.setRequiredIncludeGroupId( updatedBuff.getRequiredIncludeGroupId() );
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

	public void deleteTSecGrpInc( CFSecAuthorization Authorization,
		CFSecTSecGrpIncBuff Buff )
	{
		final String S_ProcName = "deleteTSecGrpInc";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long TSecGrpIncId = Buff.getRequiredTSecGrpIncId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_tsecinc( ?, ?, ?, ?, ?" + ", "
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
			stmtDeleteByPKey.setLong( argIdx++, TSecGrpIncId );
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

	public void deleteTSecGrpIncByIdIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argTSecGrpIncId )
	{
		final String S_ProcName = "deleteTSecGrpIncByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_tsecinc_by_ididx( ?, ?, ?, ?, ?" + ", "
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
				stmtDeleteByIdIdx.setLong( argIdx++, argTSecGrpIncId );
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

	public void deleteTSecGrpIncByIdIdx( CFSecAuthorization Authorization,
		CFSecTSecGrpIncPKey argKey )
	{
		deleteTSecGrpIncByIdIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredTSecGrpIncId() );
	}

	public void deleteTSecGrpIncByTenantIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteTSecGrpIncByTenantIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_tsecinc_by_tenantidx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteTSecGrpIncByTenantIdx( CFSecAuthorization Authorization,
		CFSecTSecGrpIncByTenantIdxKey argKey )
	{
		deleteTSecGrpIncByTenantIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteTSecGrpIncByGroupIdx( CFSecAuthorization Authorization,
		long argTenantId,
		int argTSecGroupId )
	{
		final String S_ProcName = "deleteTSecGrpIncByGroupIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_tsecinc_by_groupidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByGroupIdx == null ) {
					stmtDeleteByGroupIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByGroupIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByGroupIdx.setInt( argIdx++, argTSecGroupId );
				resultSet = stmtDeleteByGroupIdx.executeQuery();
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

	public void deleteTSecGrpIncByGroupIdx( CFSecAuthorization Authorization,
		CFSecTSecGrpIncByGroupIdxKey argKey )
	{
		deleteTSecGrpIncByGroupIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredTSecGroupId() );
	}

	public void deleteTSecGrpIncByIncludeIdx( CFSecAuthorization Authorization,
		long argTenantId,
		int argIncludeGroupId )
	{
		final String S_ProcName = "deleteTSecGrpIncByIncludeIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_tsecinc_by_includeidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByIncludeIdx == null ) {
					stmtDeleteByIncludeIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIncludeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByIncludeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByIncludeIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByIncludeIdx.setInt( argIdx++, argIncludeGroupId );
				resultSet = stmtDeleteByIncludeIdx.executeQuery();
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

	public void deleteTSecGrpIncByIncludeIdx( CFSecAuthorization Authorization,
		CFSecTSecGrpIncByIncludeIdxKey argKey )
	{
		deleteTSecGrpIncByIncludeIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredIncludeGroupId() );
	}

	public void deleteTSecGrpIncByUIncludeIdx( CFSecAuthorization Authorization,
		long argTenantId,
		int argTSecGroupId,
		int argIncludeGroupId )
	{
		final String S_ProcName = "deleteTSecGrpIncByUIncludeIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_tsecinc_by_uincludeidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUIncludeIdx == null ) {
					stmtDeleteByUIncludeIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUIncludeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUIncludeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUIncludeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUIncludeIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByUIncludeIdx.setInt( argIdx++, argTSecGroupId );
				stmtDeleteByUIncludeIdx.setInt( argIdx++, argIncludeGroupId );
				resultSet = stmtDeleteByUIncludeIdx.executeQuery();
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

	public void deleteTSecGrpIncByUIncludeIdx( CFSecAuthorization Authorization,
		CFSecTSecGrpIncByUIncludeIdxKey argKey )
	{
		deleteTSecGrpIncByUIncludeIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredTSecGroupId(),
			argKey.getRequiredIncludeGroupId() );
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
		S_sqlSelectTSecGrpIncDistinctClassCode = null;
		S_sqlSelectTSecGrpIncBuff = null;

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
		if( stmtPageBuffAll != null ) {
			try {
				stmtPageBuffAll.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffAll = null;
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
		if( stmtPageBuffByTenantIdx != null ) {
			try {
				stmtPageBuffByTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffByTenantIdx = null;
		}
		if( stmtPageBuffByGroupIdx != null ) {
			try {
				stmtPageBuffByGroupIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffByGroupIdx = null;
		}
		if( stmtPageBuffByIncludeIdx != null ) {
			try {
				stmtPageBuffByIncludeIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffByIncludeIdx = null;
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
		if( stmtDeleteByGroupIdx != null ) {
			try {
				stmtDeleteByGroupIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByGroupIdx = null;
		}
		if( stmtDeleteByIncludeIdx != null ) {
			try {
				stmtDeleteByIncludeIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByIncludeIdx = null;
		}
		if( stmtDeleteByUIncludeIdx != null ) {
			try {
				stmtDeleteByUIncludeIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUIncludeIdx = null;
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
		if( stmtReadBuffByGroupIdx != null ) {
			try {
				stmtReadBuffByGroupIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByGroupIdx = null;
		}
		if( stmtReadBuffByIncludeIdx != null ) {
			try {
				stmtReadBuffByIncludeIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByIncludeIdx = null;
		}
		if( stmtReadBuffByUIncludeIdx != null ) {
			try {
				stmtReadBuffByUIncludeIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUIncludeIdx = null;
		}
	}
}
