// Description: Java 11 PostgreSQL Jdbc DbIO implementation for SecForm.

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
 *	CFBamPgSqlSecFormTable PostgreSQL Jdbc DbIO implementation
 *	for SecForm.
 */
public class CFBamPgSqlSecFormTable
	implements ICFBamSecFormTable
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
	protected PreparedStatement stmtReadBuffBySecAppIdx = null;
	protected PreparedStatement stmtReadBuffByUJEEServletIdx = null;
	protected PreparedStatement stmtPageBuffByClusterIdx = null;
	protected PreparedStatement stmtPageBuffBySecAppIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByClusterIdx = null;
	protected PreparedStatement stmtDeleteBySecAppIdx = null;
	protected PreparedStatement stmtDeleteByUJEEServletIdx = null;

	public CFBamPgSqlSecFormTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createSecForm( CFSecAuthorization Authorization,
		CFSecSecFormBuff Buff )
	{
		final String S_ProcName = "createSecForm";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			long ClusterId = Buff.getRequiredClusterId();
			int SecAppId = Buff.getRequiredSecAppId();
			String JEEServletMapName = Buff.getRequiredJEEServletMapName();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_secform( ?, ?, ?, ?, ?, ?" + ", "
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
			stmtCreateByPKey.setString( argIdx++, "a00b" );
			stmtCreateByPKey.setLong( argIdx++, ClusterId );
			stmtCreateByPKey.setInt( argIdx++, SecAppId );
			stmtCreateByPKey.setString( argIdx++, JEEServletMapName );
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecFormBuff createdBuff = unpackSecFormResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredClusterId( createdBuff.getRequiredClusterId() );
				Buff.setRequiredSecFormId( createdBuff.getRequiredSecFormId() );
				Buff.setRequiredSecAppId( createdBuff.getRequiredSecAppId() );
				Buff.setRequiredJEEServletMapName( createdBuff.getRequiredJEEServletMapName() );
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

	public String S_sqlSelectSecFormDistinctClassCode = null;

	public String getSqlSelectSecFormDistinctClassCode() {
		if( S_sqlSelectSecFormDistinctClassCode == null ) {
			S_sqlSelectSecFormDistinctClassCode =
					"SELECT "
					+		"DISTINCT a00b.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".SecForm AS a00b ";
		}
		return( S_sqlSelectSecFormDistinctClassCode );
	}

	public String S_sqlSelectSecFormBuff = null;

	public String getSqlSelectSecFormBuff() {
		if( S_sqlSelectSecFormBuff == null ) {
			S_sqlSelectSecFormBuff =
					"SELECT "
					+		"a00b.ClusterId, "
					+		"a00b.SecFormId, "
					+		"a00b.SecAppId, "
					+		"a00b.JEEServletMapName, "
					+		"a00b.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".SecForm AS a00b ";
		}
		return( S_sqlSelectSecFormBuff );
	}

	protected CFSecSecFormBuff unpackSecFormResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackSecFormResultSetToBuff";
		int idxcol = 1;
		CFSecSecFormBuff buff = schema.getFactorySecForm().newBuff();
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
		buff.setRequiredSecFormId( resultSet.getInt( idxcol ) );
		idxcol++;
		buff.setRequiredSecAppId( resultSet.getInt( idxcol ) );
		idxcol++;
		buff.setRequiredJEEServletMapName( resultSet.getString( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFSecSecFormBuff readDerived( CFSecAuthorization Authorization,
		CFSecSecFormPKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecFormBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecSecFormBuff lockDerived( CFSecAuthorization Authorization,
		CFSecSecFormPKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecFormBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecSecFormBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFSecSecFormBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFSecSecFormBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecFormId )
	{
		final String S_ProcName = "CFBamPgSqlSecFormTable.readDerivedByIdIdx() ";
		CFSecSecFormBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByIdIdx( Authorization,
				ClusterId,
				SecFormId );
		return( buff );
	}

	public CFSecSecFormBuff[] readDerivedByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId )
	{
		final String S_ProcName = "readDerivedByClusterIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecFormBuff[] buffList = readBuffByClusterIdx( Authorization,
				ClusterId );
		return( buffList );

	}

	public CFSecSecFormBuff[] readDerivedBySecAppIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecAppId )
	{
		final String S_ProcName = "readDerivedBySecAppIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecFormBuff[] buffList = readBuffBySecAppIdx( Authorization,
				ClusterId,
				SecAppId );
		return( buffList );

	}

	public CFSecSecFormBuff readDerivedByUJEEServletIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecAppId,
		String JEEServletMapName )
	{
		final String S_ProcName = "CFBamPgSqlSecFormTable.readDerivedByUJEEServletIdx() ";
		CFSecSecFormBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUJEEServletIdx( Authorization,
				ClusterId,
				SecAppId,
				JEEServletMapName );
		return( buff );
	}

	public CFSecSecFormBuff readBuff( CFSecAuthorization Authorization,
		CFSecSecFormPKey PKey )
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
			int SecFormId = PKey.getRequiredSecFormId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secform( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByPKey.setInt( argIdx++, SecFormId );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecFormBuff buff = unpackSecFormResultSetToBuff( resultSet );
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

	public CFSecSecFormBuff lockBuff( CFSecAuthorization Authorization,
		CFSecSecFormPKey PKey )
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
			int SecFormId = PKey.getRequiredSecFormId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_secform( ?, ?, ?, ?, ?" + ", "
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
			stmtLockBuffByPKey.setInt( argIdx++, SecFormId );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecFormBuff buff = unpackSecFormResultSetToBuff( resultSet );
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

	public CFSecSecFormBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secform_all( ?, ?, ?, ?, ? )";
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
			List<CFSecSecFormBuff> buffList = new LinkedList<CFSecSecFormBuff>();
			while( resultSet.next() ) {
				CFSecSecFormBuff buff = unpackSecFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecFormBuff[] retBuff = new CFSecSecFormBuff[ buffList.size() ];
			Iterator<CFSecSecFormBuff> iter = buffList.iterator();
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
	 *	Read a page of all the specific SecForm buffer instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecForm instances in the database accessible for the Authorization.
	 */
	public CFSecSecFormBuff[] pageAllBuff( CFSecAuthorization Authorization,
		Long priorClusterId,
		Integer priorSecFormId )
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
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_secform_all( ?, ?, ?, ?, ?, ?, ? )";
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
			if( priorSecFormId != null ) {
				stmtPageBuffAll.setInt( argIdx++, priorSecFormId.intValue() );
			}
			else {
				stmtPageBuffAll.setNull( argIdx++, java.sql.Types.INTEGER );
			}
			resultSet = stmtPageBuffAll.executeQuery();
			List<CFSecSecFormBuff> buffList = new LinkedList<CFSecSecFormBuff>();
			while( resultSet.next() ) {
				CFSecSecFormBuff buff = unpackSecFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecFormBuff[] retBuff = new CFSecSecFormBuff[ buffList.size() ];
			Iterator<CFSecSecFormBuff> iter = buffList.iterator();
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

	public CFSecSecFormBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecFormId )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secform_by_ididx( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByIdIdx.setInt( argIdx++, SecFormId );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecSecFormBuff buff = unpackSecFormResultSetToBuff( resultSet );
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

	public CFSecSecFormBuff[] readBuffByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId )
	{
		final String S_ProcName = "readBuffByClusterIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secform_by_clusteridx( ?, ?, ?, ?, ?" + ", "
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
			List<CFSecSecFormBuff> buffList = new LinkedList<CFSecSecFormBuff>();
			while( resultSet.next() ) {
				CFSecSecFormBuff buff = unpackSecFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecFormBuff[] retBuff = new CFSecSecFormBuff[ buffList.size() ];
			Iterator<CFSecSecFormBuff> iter = buffList.iterator();
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

	public CFSecSecFormBuff[] readBuffBySecAppIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecAppId )
	{
		final String S_ProcName = "readBuffBySecAppIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secform_by_secappidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffBySecAppIdx == null ) {
				stmtReadBuffBySecAppIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffBySecAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffBySecAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffBySecAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffBySecAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffBySecAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffBySecAppIdx.setLong( argIdx++, ClusterId );
			stmtReadBuffBySecAppIdx.setInt( argIdx++, SecAppId );
			resultSet = stmtReadBuffBySecAppIdx.executeQuery();
			List<CFSecSecFormBuff> buffList = new LinkedList<CFSecSecFormBuff>();
			while( resultSet.next() ) {
				CFSecSecFormBuff buff = unpackSecFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecFormBuff[] retBuff = new CFSecSecFormBuff[ buffList.size() ];
			Iterator<CFSecSecFormBuff> iter = buffList.iterator();
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

	public CFSecSecFormBuff readBuffByUJEEServletIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecAppId,
		String JEEServletMapName )
	{
		final String S_ProcName = "readBuffByUJEEServletIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secform_by_ujeeservletidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUJEEServletIdx == null ) {
				stmtReadBuffByUJEEServletIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUJEEServletIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUJEEServletIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUJEEServletIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUJEEServletIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUJEEServletIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUJEEServletIdx.setLong( argIdx++, ClusterId );
			stmtReadBuffByUJEEServletIdx.setInt( argIdx++, SecAppId );
			stmtReadBuffByUJEEServletIdx.setString( argIdx++, JEEServletMapName );
			resultSet = stmtReadBuffByUJEEServletIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecSecFormBuff buff = unpackSecFormResultSetToBuff( resultSet );
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
	 *	Read a page array of the specific SecForm buffer instances identified by the duplicate key ClusterIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argClusterId	The SecForm key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecSecFormBuff[] pageBuffByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId,
		Long priorClusterId,
		Integer priorSecFormId )
	{
		final String S_ProcName = "pageBuffByClusterIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_secform_by_clusteridx( ?, ?, ?, ?, ?" + ", "
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
			if( priorSecFormId != null ) {
				stmtPageBuffByClusterIdx.setInt( argIdx++, priorSecFormId.intValue() );
			}
			else {
				stmtPageBuffByClusterIdx.setNull( argIdx++, java.sql.Types.INTEGER );
			}
			resultSet = stmtPageBuffByClusterIdx.executeQuery();
			List<CFSecSecFormBuff> buffList = new LinkedList<CFSecSecFormBuff>();
			while( resultSet.next() ) {
				CFSecSecFormBuff buff = unpackSecFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecFormBuff[] retBuff = new CFSecSecFormBuff[ buffList.size() ];
			Iterator<CFSecSecFormBuff> iter = buffList.iterator();
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
	 *	Read a page array of the specific SecForm buffer instances identified by the duplicate key SecAppIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argClusterId	The SecForm key attribute of the instance generating the id.
	 *
	 *	@param	argSecAppId	The SecForm key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecSecFormBuff[] pageBuffBySecAppIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecAppId,
		Long priorClusterId,
		Integer priorSecFormId )
	{
		final String S_ProcName = "pageBuffBySecAppIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_secform_by_secappidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtPageBuffBySecAppIdx == null ) {
				stmtPageBuffBySecAppIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffBySecAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffBySecAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffBySecAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffBySecAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffBySecAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtPageBuffBySecAppIdx.setLong( argIdx++, ClusterId );
			stmtPageBuffBySecAppIdx.setInt( argIdx++, SecAppId );
			if( priorClusterId != null ) {
				stmtPageBuffBySecAppIdx.setLong( argIdx++, priorClusterId.longValue() );
			}
			else {
				stmtPageBuffBySecAppIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorSecFormId != null ) {
				stmtPageBuffBySecAppIdx.setInt( argIdx++, priorSecFormId.intValue() );
			}
			else {
				stmtPageBuffBySecAppIdx.setNull( argIdx++, java.sql.Types.INTEGER );
			}
			resultSet = stmtPageBuffBySecAppIdx.executeQuery();
			List<CFSecSecFormBuff> buffList = new LinkedList<CFSecSecFormBuff>();
			while( resultSet.next() ) {
				CFSecSecFormBuff buff = unpackSecFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecFormBuff[] retBuff = new CFSecSecFormBuff[ buffList.size() ];
			Iterator<CFSecSecFormBuff> iter = buffList.iterator();
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

	public void updateSecForm( CFSecAuthorization Authorization,
		CFSecSecFormBuff Buff )
	{
		final String S_ProcName = "updateSecForm";
		ResultSet resultSet = null;
		try {
			long ClusterId = Buff.getRequiredClusterId();
			int SecFormId = Buff.getRequiredSecFormId();
			int SecAppId = Buff.getRequiredSecAppId();
			String JEEServletMapName = Buff.getRequiredJEEServletMapName();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_secform( ?, ?, ?, ?, ?, ?" + ", "
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
			stmtUpdateByPKey.setString( argIdx++, "a00b" );
			stmtUpdateByPKey.setLong( argIdx++, ClusterId );
			stmtUpdateByPKey.setInt( argIdx++, SecFormId );
			stmtUpdateByPKey.setInt( argIdx++, SecAppId );
			stmtUpdateByPKey.setString( argIdx++, JEEServletMapName );
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecFormBuff updatedBuff = unpackSecFormResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredSecAppId( updatedBuff.getRequiredSecAppId() );
				Buff.setRequiredJEEServletMapName( updatedBuff.getRequiredJEEServletMapName() );
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

	public void deleteSecForm( CFSecAuthorization Authorization,
		CFSecSecFormBuff Buff )
	{
		final String S_ProcName = "deleteSecForm";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long ClusterId = Buff.getRequiredClusterId();
			int SecFormId = Buff.getRequiredSecFormId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secform( ?, ?, ?, ?, ?" + ", "
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
			stmtDeleteByPKey.setInt( argIdx++, SecFormId );
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

	public void deleteSecFormByIdIdx( CFSecAuthorization Authorization,
		long argClusterId,
		int argSecFormId )
	{
		final String S_ProcName = "deleteSecFormByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secform_by_ididx( ?, ?, ?, ?, ?" + ", "
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
				stmtDeleteByIdIdx.setInt( argIdx++, argSecFormId );
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

	public void deleteSecFormByIdIdx( CFSecAuthorization Authorization,
		CFSecSecFormPKey argKey )
	{
		deleteSecFormByIdIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredSecFormId() );
	}

	public void deleteSecFormByClusterIdx( CFSecAuthorization Authorization,
		long argClusterId )
	{
		final String S_ProcName = "deleteSecFormByClusterIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secform_by_clusteridx( ?, ?, ?, ?, ?" + ", "
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

	public void deleteSecFormByClusterIdx( CFSecAuthorization Authorization,
		CFSecSecFormByClusterIdxKey argKey )
	{
		deleteSecFormByClusterIdx( Authorization,
			argKey.getRequiredClusterId() );
	}

	public void deleteSecFormBySecAppIdx( CFSecAuthorization Authorization,
		long argClusterId,
		int argSecAppId )
	{
		final String S_ProcName = "deleteSecFormBySecAppIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secform_by_secappidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteBySecAppIdx == null ) {
					stmtDeleteBySecAppIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteBySecAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteBySecAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteBySecAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteBySecAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteBySecAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteBySecAppIdx.setLong( argIdx++, argClusterId );
				stmtDeleteBySecAppIdx.setInt( argIdx++, argSecAppId );
				resultSet = stmtDeleteBySecAppIdx.executeQuery();
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

	public void deleteSecFormBySecAppIdx( CFSecAuthorization Authorization,
		CFSecSecFormBySecAppIdxKey argKey )
	{
		deleteSecFormBySecAppIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredSecAppId() );
	}

	public void deleteSecFormByUJEEServletIdx( CFSecAuthorization Authorization,
		long argClusterId,
		int argSecAppId,
		String argJEEServletMapName )
	{
		final String S_ProcName = "deleteSecFormByUJEEServletIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secform_by_ujeeservletidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUJEEServletIdx == null ) {
					stmtDeleteByUJEEServletIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUJEEServletIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUJEEServletIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUJEEServletIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUJEEServletIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUJEEServletIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUJEEServletIdx.setLong( argIdx++, argClusterId );
				stmtDeleteByUJEEServletIdx.setInt( argIdx++, argSecAppId );
				stmtDeleteByUJEEServletIdx.setString( argIdx++, argJEEServletMapName );
				resultSet = stmtDeleteByUJEEServletIdx.executeQuery();
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

	public void deleteSecFormByUJEEServletIdx( CFSecAuthorization Authorization,
		CFSecSecFormByUJEEServletIdxKey argKey )
	{
		deleteSecFormByUJEEServletIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredSecAppId(),
			argKey.getRequiredJEEServletMapName() );
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
		S_sqlSelectSecFormDistinctClassCode = null;
		S_sqlSelectSecFormBuff = null;

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
		if( stmtPageBuffBySecAppIdx != null ) {
			try {
				stmtPageBuffBySecAppIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffBySecAppIdx = null;
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
		if( stmtDeleteBySecAppIdx != null ) {
			try {
				stmtDeleteBySecAppIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteBySecAppIdx = null;
		}
		if( stmtDeleteByUJEEServletIdx != null ) {
			try {
				stmtDeleteByUJEEServletIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUJEEServletIdx = null;
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
		if( stmtReadBuffBySecAppIdx != null ) {
			try {
				stmtReadBuffBySecAppIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffBySecAppIdx = null;
		}
		if( stmtReadBuffByUJEEServletIdx != null ) {
			try {
				stmtReadBuffByUJEEServletIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUJEEServletIdx = null;
		}
	}
}
