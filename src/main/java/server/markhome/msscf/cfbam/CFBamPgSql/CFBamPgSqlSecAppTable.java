// Description: Java 11 PostgreSQL Jdbc DbIO implementation for SecApp.

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
 *	CFBamPgSqlSecAppTable PostgreSQL Jdbc DbIO implementation
 *	for SecApp.
 */
public class CFBamPgSqlSecAppTable
	implements ICFBamSecAppTable
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
	protected PreparedStatement stmtReadBuffByClusterIdx = null;
	protected PreparedStatement stmtReadBuffByUJEEMountIdx = null;
	protected PreparedStatement stmtPageBuffByClusterIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByClusterIdx = null;
	protected PreparedStatement stmtDeleteByUJEEMountIdx = null;

	public CFBamPgSqlSecAppTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createSecApp( CFSecAuthorization Authorization,
		CFSecSecAppBuff Buff )
	{
		final String S_ProcName = "createSecApp";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			long ClusterId = Buff.getRequiredClusterId();
			String JEEMountName = Buff.getRequiredJEEMountName();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_secapp( ?, ?, ?, ?, ?, ?" + ", "
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
			stmtCreateByPKey.setString( argIdx++, "a009" );
			stmtCreateByPKey.setLong( argIdx++, ClusterId );
			stmtCreateByPKey.setString( argIdx++, JEEMountName );
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecAppBuff createdBuff = unpackSecAppResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredClusterId( createdBuff.getRequiredClusterId() );
				Buff.setRequiredSecAppId( createdBuff.getRequiredSecAppId() );
				Buff.setRequiredJEEMountName( createdBuff.getRequiredJEEMountName() );
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

	public String S_sqlSelectSecAppDistinctClassCode = null;

	public String getSqlSelectSecAppDistinctClassCode() {
		if( S_sqlSelectSecAppDistinctClassCode == null ) {
			S_sqlSelectSecAppDistinctClassCode =
					"SELECT "
					+		"DISTINCT a009.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".SecApp AS a009 ";
		}
		return( S_sqlSelectSecAppDistinctClassCode );
	}

	public String S_sqlSelectSecAppBuff = null;

	public String getSqlSelectSecAppBuff() {
		if( S_sqlSelectSecAppBuff == null ) {
			S_sqlSelectSecAppBuff =
					"SELECT "
					+		"a009.ClusterId, "
					+		"a009.SecAppId, "
					+		"a009.JEEMountName, "
					+		"a009.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".SecApp AS a009 ";
		}
		return( S_sqlSelectSecAppBuff );
	}

	protected CFSecSecAppBuff unpackSecAppResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackSecAppResultSetToBuff";
		int idxcol = 1;
		CFSecSecAppBuff buff = schema.getFactorySecApp().newBuff();
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
		buff.setRequiredClusterId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredSecAppId( resultSet.getInt( idxcol ) );
		idxcol++;
		buff.setRequiredJEEMountName( resultSet.getString( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFSecSecAppBuff readDerived( CFSecAuthorization Authorization,
		CFSecSecAppPKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecAppBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecSecAppBuff lockDerived( CFSecAuthorization Authorization,
		CFSecSecAppPKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecAppBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecSecAppBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFSecSecAppBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFSecSecAppBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecAppId )
	{
		final String S_ProcName = "CFBamPgSqlSecAppTable.readDerivedByIdIdx() ";
		CFSecSecAppBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByIdIdx( Authorization,
				ClusterId,
				SecAppId );
		return( buff );
	}

	public CFSecSecAppBuff[] readDerivedByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId )
	{
		final String S_ProcName = "readDerivedByClusterIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecAppBuff[] buffList = readBuffByClusterIdx( Authorization,
				ClusterId );
		return( buffList );

	}

	public CFSecSecAppBuff readDerivedByUJEEMountIdx( CFSecAuthorization Authorization,
		long ClusterId,
		String JEEMountName )
	{
		final String S_ProcName = "CFBamPgSqlSecAppTable.readDerivedByUJEEMountIdx() ";
		CFSecSecAppBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUJEEMountIdx( Authorization,
				ClusterId,
				JEEMountName );
		return( buff );
	}

	public CFSecSecAppBuff readBuff( CFSecAuthorization Authorization,
		CFSecSecAppPKey PKey )
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
			long ClusterId = PKey.getRequiredClusterId();
			int SecAppId = PKey.getRequiredSecAppId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secapp( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByPKey.setLong( argIdx++, ClusterId );
			stmtReadBuffByPKey.setInt( argIdx++, SecAppId );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecAppBuff buff = unpackSecAppResultSetToBuff( resultSet );
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

	public CFSecSecAppBuff lockBuff( CFSecAuthorization Authorization,
		CFSecSecAppPKey PKey )
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
			long ClusterId = PKey.getRequiredClusterId();
			int SecAppId = PKey.getRequiredSecAppId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_secapp( ?, ?, ?, ?, ?" + ", "
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
			stmtLockBuffByPKey.setLong( argIdx++, ClusterId );
			stmtLockBuffByPKey.setInt( argIdx++, SecAppId );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecAppBuff buff = unpackSecAppResultSetToBuff( resultSet );
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

	public CFSecSecAppBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secapp_all( ?, ?, ?, ?, ? )";
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
			List<CFSecSecAppBuff> buffList = new LinkedList<CFSecSecAppBuff>();
			while( resultSet.next() ) {
				CFSecSecAppBuff buff = unpackSecAppResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecAppBuff[] retBuff = new CFSecSecAppBuff[ buffList.size() ];
			Iterator<CFSecSecAppBuff> iter = buffList.iterator();
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
	 *	Read a page of all the specific SecApp buffer instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecApp instances in the database accessible for the Authorization.
	 */
	public CFSecSecAppBuff[] pageAllBuff( CFSecAuthorization Authorization,
		Long priorClusterId,
		Integer priorSecAppId )
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_secapp_all( ?, ?, ?, ?, ?, ?, ? )";
			if( stmtPageBuffAll == null ) {
				stmtPageBuffAll = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffAll.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffAll.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( priorClusterId != null ) {
				stmtPageBuffAll.setLong( argIdx++, priorClusterId.longValue() );
			}
			else {
				stmtPageBuffAll.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorSecAppId != null ) {
				stmtPageBuffAll.setInt( argIdx++, priorSecAppId.intValue() );
			}
			else {
				stmtPageBuffAll.setNull( argIdx++, java.sql.Types.INTEGER );
			}
			resultSet = stmtPageBuffAll.executeQuery();
			List<CFSecSecAppBuff> buffList = new LinkedList<CFSecSecAppBuff>();
			while( resultSet.next() ) {
				CFSecSecAppBuff buff = unpackSecAppResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecAppBuff[] retBuff = new CFSecSecAppBuff[ buffList.size() ];
			Iterator<CFSecSecAppBuff> iter = buffList.iterator();
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

	public CFSecSecAppBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecAppId )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secapp_by_ididx( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByIdIdx.setLong( argIdx++, ClusterId );
			stmtReadBuffByIdIdx.setInt( argIdx++, SecAppId );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecSecAppBuff buff = unpackSecAppResultSetToBuff( resultSet );
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

	public CFSecSecAppBuff[] readBuffByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId )
	{
		final String S_ProcName = "readBuffByClusterIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secapp_by_clusteridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByClusterIdx == null ) {
				stmtReadBuffByClusterIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByClusterIdx.setLong( argIdx++, ClusterId );
			resultSet = stmtReadBuffByClusterIdx.executeQuery();
			List<CFSecSecAppBuff> buffList = new LinkedList<CFSecSecAppBuff>();
			while( resultSet.next() ) {
				CFSecSecAppBuff buff = unpackSecAppResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecAppBuff[] retBuff = new CFSecSecAppBuff[ buffList.size() ];
			Iterator<CFSecSecAppBuff> iter = buffList.iterator();
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

	public CFSecSecAppBuff readBuffByUJEEMountIdx( CFSecAuthorization Authorization,
		long ClusterId,
		String JEEMountName )
	{
		final String S_ProcName = "readBuffByUJEEMountIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secapp_by_ujeemountidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUJEEMountIdx == null ) {
				stmtReadBuffByUJEEMountIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUJEEMountIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUJEEMountIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUJEEMountIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUJEEMountIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUJEEMountIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUJEEMountIdx.setLong( argIdx++, ClusterId );
			stmtReadBuffByUJEEMountIdx.setString( argIdx++, JEEMountName );
			resultSet = stmtReadBuffByUJEEMountIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecSecAppBuff buff = unpackSecAppResultSetToBuff( resultSet );
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
	 *	Read a page array of the specific SecApp buffer instances identified by the duplicate key ClusterIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argClusterId	The SecApp key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecSecAppBuff[] pageBuffByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId,
		Long priorClusterId,
		Integer priorSecAppId )
	{
		final String S_ProcName = "pageBuffByClusterIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_secapp_by_clusteridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtPageBuffByClusterIdx == null ) {
				stmtPageBuffByClusterIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtPageBuffByClusterIdx.setLong( argIdx++, ClusterId );
			if( priorClusterId != null ) {
				stmtPageBuffByClusterIdx.setLong( argIdx++, priorClusterId.longValue() );
			}
			else {
				stmtPageBuffByClusterIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorSecAppId != null ) {
				stmtPageBuffByClusterIdx.setInt( argIdx++, priorSecAppId.intValue() );
			}
			else {
				stmtPageBuffByClusterIdx.setNull( argIdx++, java.sql.Types.INTEGER );
			}
			resultSet = stmtPageBuffByClusterIdx.executeQuery();
			List<CFSecSecAppBuff> buffList = new LinkedList<CFSecSecAppBuff>();
			while( resultSet.next() ) {
				CFSecSecAppBuff buff = unpackSecAppResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecAppBuff[] retBuff = new CFSecSecAppBuff[ buffList.size() ];
			Iterator<CFSecSecAppBuff> iter = buffList.iterator();
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

	public void updateSecApp( CFSecAuthorization Authorization,
		CFSecSecAppBuff Buff )
	{
		final String S_ProcName = "updateSecApp";
		ResultSet resultSet = null;
		try {
			long ClusterId = Buff.getRequiredClusterId();
			int SecAppId = Buff.getRequiredSecAppId();
			String JEEMountName = Buff.getRequiredJEEMountName();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_secapp( ?, ?, ?, ?, ?, ?" + ", "
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
			stmtUpdateByPKey.setString( argIdx++, "a009" );
			stmtUpdateByPKey.setLong( argIdx++, ClusterId );
			stmtUpdateByPKey.setInt( argIdx++, SecAppId );
			stmtUpdateByPKey.setString( argIdx++, JEEMountName );
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecAppBuff updatedBuff = unpackSecAppResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredJEEMountName( updatedBuff.getRequiredJEEMountName() );
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

	public void deleteSecApp( CFSecAuthorization Authorization,
		CFSecSecAppBuff Buff )
	{
		final String S_ProcName = "deleteSecApp";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long ClusterId = Buff.getRequiredClusterId();
			int SecAppId = Buff.getRequiredSecAppId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secapp( ?, ?, ?, ?, ?" + ", "
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
			stmtDeleteByPKey.setLong( argIdx++, ClusterId );
			stmtDeleteByPKey.setInt( argIdx++, SecAppId );
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

	public void deleteSecAppByIdIdx( CFSecAuthorization Authorization,
		long argClusterId,
		int argSecAppId )
	{
		final String S_ProcName = "deleteSecAppByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secapp_by_ididx( ?, ?, ?, ?, ?" + ", "
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
				stmtDeleteByIdIdx.setLong( argIdx++, argClusterId );
				stmtDeleteByIdIdx.setInt( argIdx++, argSecAppId );
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

	public void deleteSecAppByIdIdx( CFSecAuthorization Authorization,
		CFSecSecAppPKey argKey )
	{
		deleteSecAppByIdIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredSecAppId() );
	}

	public void deleteSecAppByClusterIdx( CFSecAuthorization Authorization,
		long argClusterId )
	{
		final String S_ProcName = "deleteSecAppByClusterIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secapp_by_clusteridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByClusterIdx == null ) {
					stmtDeleteByClusterIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByClusterIdx.setLong( argIdx++, argClusterId );
				resultSet = stmtDeleteByClusterIdx.executeQuery();
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

	public void deleteSecAppByClusterIdx( CFSecAuthorization Authorization,
		CFSecSecAppByClusterIdxKey argKey )
	{
		deleteSecAppByClusterIdx( Authorization,
			argKey.getRequiredClusterId() );
	}

	public void deleteSecAppByUJEEMountIdx( CFSecAuthorization Authorization,
		long argClusterId,
		String argJEEMountName )
	{
		final String S_ProcName = "deleteSecAppByUJEEMountIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secapp_by_ujeemountidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUJEEMountIdx == null ) {
					stmtDeleteByUJEEMountIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUJEEMountIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUJEEMountIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUJEEMountIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUJEEMountIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUJEEMountIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUJEEMountIdx.setLong( argIdx++, argClusterId );
				stmtDeleteByUJEEMountIdx.setString( argIdx++, argJEEMountName );
				resultSet = stmtDeleteByUJEEMountIdx.executeQuery();
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

	public void deleteSecAppByUJEEMountIdx( CFSecAuthorization Authorization,
		CFSecSecAppByUJEEMountIdxKey argKey )
	{
		deleteSecAppByUJEEMountIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredJEEMountName() );
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
		S_sqlSelectSecAppDistinctClassCode = null;
		S_sqlSelectSecAppBuff = null;

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
		if( stmtPageBuffByClusterIdx != null ) {
			try {
				stmtPageBuffByClusterIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffByClusterIdx = null;
		}
		if( stmtDeleteByIdIdx != null ) {
			try {
				stmtDeleteByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByIdIdx = null;
		}
		if( stmtDeleteByClusterIdx != null ) {
			try {
				stmtDeleteByClusterIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByClusterIdx = null;
		}
		if( stmtDeleteByUJEEMountIdx != null ) {
			try {
				stmtDeleteByUJEEMountIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUJEEMountIdx = null;
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
		if( stmtReadBuffByClusterIdx != null ) {
			try {
				stmtReadBuffByClusterIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByClusterIdx = null;
		}
		if( stmtReadBuffByUJEEMountIdx != null ) {
			try {
				stmtReadBuffByUJEEMountIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUJEEMountIdx = null;
		}
	}
}
